package com.yusufsoysal.strava.auth;

import com.yusufsoysal.strava.model.StravaUserDetails;
import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.service.UserService;
import javastrava.api.v3.model.StravaAthlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Transactional
    public void userLoggedIn(StravaUserDetails userDetails) {
        StravaAthlete athlete = userDetails.getAthlete();

        User existingUser = userService.getUserByStravaId(athlete.getId());
        if (existingUser == null) {
            userService.createUser(userDetails);
        } else {
            userService.updateUser(existingUser, userDetails);
        }
    }

}
