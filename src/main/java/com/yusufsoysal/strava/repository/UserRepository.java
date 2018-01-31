package com.yusufsoysal.strava.repository;

import com.yusufsoysal.strava.model.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByStravaId(Integer stravaId);

    User findByUsername(String username);

    List<User> findUsersByLastSynchedTimeBeforeAndLastLoginTimeAfter(LocalDateTime lastSynchedTime, LocalDateTime lastLoginTime);
}
