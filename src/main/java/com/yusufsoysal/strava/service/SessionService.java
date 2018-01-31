package com.yusufsoysal.strava.service;

import com.yusufsoysal.strava.auth.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class SessionService {

    @Autowired
    private AuthenticationFacade authFacade;

    public boolean isLoggedIn(){
        Authentication authentication = authFacade.getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return true;
        }

        return false;
    }

}
