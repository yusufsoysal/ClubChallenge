package com.yusufsoysal.strava.service;

import com.yusufsoysal.strava.model.Club;
import com.yusufsoysal.strava.model.Gear;
import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.repository.ClubRepository;
import com.yusufsoysal.strava.repository.GearRepository;
import javastrava.api.v3.model.StravaClub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClubService {
    @Autowired
    private ClubRepository repository;

    @Transactional
    public Club getClub(Integer id){
        return repository.findOne(id);
    }

    @Transactional
    public Club getOrCreate(StravaClub stravaClub){
        Club club = getClub(stravaClub.getId());
        if( club != null ){
            return club;
        }

        club = new Club(stravaClub);
        repository.save(club);

        return club;
    }

    @Transactional
    public void clubSynched(Club club){
        club.setLastSynchedTime(LocalDateTime.now());
        repository.save(club);
    }

}
