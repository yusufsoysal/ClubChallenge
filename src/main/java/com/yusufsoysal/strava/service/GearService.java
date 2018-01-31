package com.yusufsoysal.strava.service;

import com.yusufsoysal.strava.model.Gear;
import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.repository.GearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GearService {
    @Autowired
    private GearRepository repository;

    @Transactional
    public Gear getGear(String gearId){
        return repository.findOne(gearId);
    }

    @Transactional
    public void assignGearsToUser(User user, List<Gear> gears){
        gears.forEach(gear -> gear.setUser(user));
        repository.save(gears);
    }

    @Transactional
    public void removeGear(Gear gear) {
        repository.delete(gear);
    }
}
