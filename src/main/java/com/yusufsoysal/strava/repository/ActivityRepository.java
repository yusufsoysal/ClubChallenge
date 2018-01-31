package com.yusufsoysal.strava.repository;

import com.yusufsoysal.strava.model.Activity;
import com.yusufsoysal.strava.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {
}
