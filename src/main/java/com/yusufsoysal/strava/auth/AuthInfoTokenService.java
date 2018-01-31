package com.yusufsoysal.strava.auth;

import com.yusufsoysal.strava.model.StravaUserDetails;
import com.yusufsoysal.strava.service.StravaService;
import javastrava.api.v3.model.StravaAthlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthInfoTokenService extends UserInfoTokenServices {

    private StravaService stravaService;
    private AuthenticationService authService;
    private RememberMeServices rememberMeServices;

    @Autowired
    public AuthInfoTokenService(@Qualifier("stravaServerProperties") ResourceServerProperties serverProperties,
                                AuthorizationCodeResourceDetails resource,
                                OAuth2RestTemplate restTemplate,
                                StravaService stravaService,
                                AuthenticationService authService) {
        super(serverProperties.getUserInfoUri(), resource.getClientId());
        this.stravaService = stravaService;
        this.authService = authService;

        setRestTemplate(restTemplate);
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        OAuth2Authentication oAuth2Authentication = super.loadAuthentication(accessToken);

        StravaAthlete athleteDetails = stravaService.getAthleteDetails(accessToken, oAuth2Authentication);
        authService.userLoggedIn(new StravaUserDetails(athleteDetails, accessToken, (String) oAuth2Authentication.getPrincipal()));

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        rememberMeServices.loginSuccess(requestAttributes.getRequest(), requestAttributes.getResponse(), oAuth2Authentication);

        return oAuth2Authentication;
    }
}
