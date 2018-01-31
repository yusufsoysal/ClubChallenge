package com.yusufsoysal.strava.service;

import com.yusufsoysal.strava.TokenImpl;
import com.yusufsoysal.strava.model.Club;
import com.yusufsoysal.strava.model.User;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StravaService {

    public StravaAthlete getAthleteDetails(String accessToken, OAuth2Authentication auth){
        Token token = new TokenImpl(accessToken, auth);

        Strava strava = new Strava(token);

        return strava.getAuthenticatedAthlete();
    }

    public List<StravaActivity> getActivities(User user){
        Token token = new TokenImpl(user);
        Strava strava = new Strava(token);

        return strava.listAllAuthenticatedAthleteActivities();
    }

    public List<StravaActivity> getActivities(User user, Club club) {
        Token token = new TokenImpl(user);
        Strava strava = new Strava(token);

        return strava.listAllRecentClubActivities(club.getId());
    }

}
