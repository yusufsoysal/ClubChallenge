package com.yusufsoysal.strava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Configuration
public class AuthConfig {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("strava.client")
    public AuthorizationCodeResourceDetails authCodeResourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("strava.resource")
    public ResourceServerProperties stravaServerProperties() {
        return new ResourceServerProperties();
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        AuthorizationCodeResourceDetails resource = authCodeResourceDetails();
        return new OAuth2RestTemplate(resource, oauth2ClientContext);
    }

}
