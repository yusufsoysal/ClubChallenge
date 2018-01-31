package com.yusufsoysal.strava.service.sync;

import com.yusufsoysal.strava.model.Club;
import com.yusufsoysal.strava.model.Gear;
import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.service.*;
import javastrava.api.v3.model.StravaActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SyncService {

    @Autowired
    private UserService userService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private GearService gearService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private StravaService stravaService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void syncUser(User user) {
        List<StravaActivity> activities = stravaService.getActivities(user);

        for (StravaActivity stravaActivity : activities) {
            Gear gear = gearService.getGear(stravaActivity.getGearId());

            activityService.createActivity(stravaActivity, user, gear);
        }

        syncUserClubActivities(user);

        userService.userSynched(user);
    }

    private void syncUserClubActivities(User user) {
        for (Club club : user.getClubs()) {
            if (club.getLastSynchedTime() == null || LocalDateTime.now().minusDays(1).isBefore(club.getLastSynchedTime())) {
                List<StravaActivity> activities = stravaService.getActivities(user, club);

                for (StravaActivity activity : activities) {
                    User activityUser = userService.getUserByStravaId(activity.getAthlete().getId());
                    if (activityUser == null) {
                        activityUser = userService.createNonActiveUser(activity.getAthlete());
                    }

                    activityService.createActivity(activity, activityUser);
                }

                clubService.clubSynched(club);
            }
        }
    }

}
