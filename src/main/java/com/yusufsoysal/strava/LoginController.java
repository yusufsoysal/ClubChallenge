package com.yusufsoysal.strava;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.model.StravaClub;
import javastrava.api.v3.service.Strava;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

//    @RequestMapping(value = "/test")
//    @ResponseBody
//    public List<Activity> login(HttpServletRequest request, Principal principal, HttpSession session){
//        Token token = new TokenImpl("c4fe76fa8e6c19036ae659f329958d112d930c1d", principal);
//
//        Strava strava = new Strava(token);
//        StravaAthlete athlete = strava.getAuthenticatedAthlete();
//        System.out.println(athlete.getId());
//
//        StravaClub club = strava.getClub(303565);
//        System.out.println(club.getId());
//
//        List<StravaActivity> stravaActivities = strava.listAllRecentClubActivities(303565);
//        List<Activity> activityList = new ArrayList<>();
//        for (StravaActivity activity : stravaActivities) {
//            Activity act = new Activity();
//            StravaAthlete ath = activity.getAthlete();
//            act.setAthlete(ath.getFirstname() + " " + ath.getLastname());
//            act.setDistance(activity.getDistance());
//            activityList.add(act);
//        }
//
//        return activityList;
//    }

}
