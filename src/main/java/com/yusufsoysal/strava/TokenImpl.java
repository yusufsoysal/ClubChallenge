package com.yusufsoysal.strava;

import com.yusufsoysal.strava.model.User;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaAthlete;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.security.Principal;
import java.util.Map;

public class TokenImpl extends Token {

    private static final String TOKEN_TYPE = "Bearer";

    public TokenImpl(String token, Principal principal) {
        super(new TokenResponseImpl(token, principal));
    }

    public TokenImpl(String token, OAuth2Authentication auth) {
        super(new TokenResponseImpl(token, auth));
    }

    public TokenImpl(User user){
        super(new TokenResponseImpl(user));
    }

    private static class TokenResponseImpl extends TokenResponse {

        TokenResponseImpl(String token, Principal principal) {
            this(token, (OAuth2Authentication) principal);
        }

        TokenResponseImpl(String token, OAuth2Authentication auth) {
            Map<String, Object> details = (Map<String, Object>) auth.getUserAuthentication().getDetails();
            StravaAthlete athlete = new StravaAthlete();
            athlete.setEmail((String) details.get("email"));
            athlete.setId((Integer) details.get("id"));
            setAccessToken(token);
            setTokenType(TOKEN_TYPE);
            setAthlete(athlete);
        }

        TokenResponseImpl(User user){
            StravaAthlete athlete = new StravaAthlete();
            athlete.setEmail(user.getEmail());
            athlete.setId(user.getStravaId());
            setAccessToken(user.getAccessToken());
            setTokenType(TOKEN_TYPE);
            setAthlete(athlete);
        }

    }


}
