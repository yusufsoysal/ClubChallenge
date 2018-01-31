package com.yusufsoysal.strava.auth;

import com.yusufsoysal.strava.model.User;
import com.yusufsoysal.strava.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class StravaRememberMeServices extends PersistentTokenBasedRememberMeServices {

    private final UserService userService;

    public StravaRememberMeServices(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
        this.userService = (UserService) userDetailsService;
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) super.processAutoLoginCookie(cookieTokens, request, response);
        if( user.getLastLoginTime() == null || LocalDateTime.now().minusHours(1).isBefore(user.getLastLoginTime())){
            userService.userLoggedIn(user);

        }

        return user;
    }
}
