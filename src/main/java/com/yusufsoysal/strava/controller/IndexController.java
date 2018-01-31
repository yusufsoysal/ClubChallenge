package com.yusufsoysal.strava.controller;

import com.yusufsoysal.strava.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private SessionService sessionService;

    @GetMapping({"/", "/index", "/loginPage"})
    public String login() {
        return "login";
    }

//    @GetMapping({"/", "/index"})
//    public String index(){
//        if(sessionService.isLoggedIn()){
//            return "index";
//        }
//
//        return "login";
//    }
}
