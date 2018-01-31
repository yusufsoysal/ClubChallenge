package com.yusufsoysal.strava.model;

import javastrava.api.v3.model.StravaAthlete;

public class StravaUserDetails {
    private StravaAthlete athlete;
    private String accesstoken;
    private String username;

    public StravaUserDetails(StravaAthlete athlete, String accesstoken, String username) {
        this.athlete = athlete;
        this.accesstoken = accesstoken;
        this.username = username;
    }

    public StravaAthlete getAthlete() {
        return athlete;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public String getUsername() {
        return username;
    }
}
