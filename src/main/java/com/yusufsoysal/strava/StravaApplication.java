package com.yusufsoysal.strava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
//@EnableOAuth2Sso
@RestController
@EnableOAuth2Client
public class StravaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StravaApplication.class, args);
    }

}
