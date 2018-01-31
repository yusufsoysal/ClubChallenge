package com.yusufsoysal.strava.schedulers;

import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.service.UserService;
import com.yusufsoysal.strava.service.sync.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivitySyncScheduler {

    @Autowired
    private UserService userService;

    @Autowired
    private SyncService syncService;

    @Transactional
    @Scheduled(initialDelay = 10000, fixedDelay = 30000)
    public void run(){
        Iterable<User> users = userService.getUsersToSync();

        users.forEach(syncService::syncUser);
    }

}
