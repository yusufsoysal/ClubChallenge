package com.yusufsoysal.strava.config;

import com.yusufsoysal.strava.auth.AuthInfoTokenService;
import com.yusufsoysal.strava.auth.StravaRememberMeServices;
import com.yusufsoysal.strava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String REMEMBER_ME_KEY = "7c3c0b9d-0bd3-4716-8ac7-a2c03e73d843";

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Autowired
    private AuthInfoTokenService userInfoTokenService;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Filter ssoFilter = ssoFilter();

        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/js/**", "/img/**", "/css/**")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices())
                .key(REMEMBER_ME_KEY)

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()

                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                .and()
                .addFilterBefore(ssoFilter, BasicAuthenticationFilter.class);
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter stravaFilter = new OAuth2ClientAuthenticationProcessingFilter("/login");
        stravaFilter.setRestTemplate(restTemplate);
        stravaFilter.setTokenServices(userInfoTokenService);

        return stravaFilter;
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        PersistentTokenBasedRememberMeServices rememberMeServices = new StravaRememberMeServices(REMEMBER_ME_KEY, userDetailsService, persistentTokenRepository());
        rememberMeServices.setAlwaysRemember(true);
        rememberMeServices.setCookieName("strRem");
        rememberMeServices.setTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7));

        userInfoTokenService.setRememberMeServices(rememberMeServices);

        return rememberMeServices;

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }
}
