package com.yusufsoysal.strava.service;

import com.yusufsoysal.strava.model.Activity;
import com.yusufsoysal.strava.model.Gear;
import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.repository.ActivityRepository;
import javastrava.api.v3.model.StravaActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository repository;

    @Transactional
    public Activity createActivity(StravaActivity stravaActivity, User user){
        return createActivity(stravaActivity, user, null);
    }

    @Transactional
    public Activity createActivity(StravaActivity stravaActivity, User user, Gear gear){
        Activity existingActivity = repository.findOne(stravaActivity.getId());
        if( existingActivity != null ){
            Activity activity = new Activity(stravaActivity, user, gear);

            return repository.save(activity);
        }

        return existingActivity;
    }

}
