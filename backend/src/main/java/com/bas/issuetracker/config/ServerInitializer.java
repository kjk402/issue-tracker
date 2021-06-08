package com.bas.issuetracker.config;

import com.bas.issuetracker.config.properties.OAuthSecret;
import com.bas.issuetracker.config.properties.GithubApi;
import com.bas.issuetracker.config.properties.JwtSecret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerInitializer implements CommandLineRunner {

    private final OAuthSecret OAuthSecret;
    private final GithubApi githubApi;
    private final JwtSecret jwtSecret;

    public ServerInitializer(OAuthSecret OAuthSecret, GithubApi githubApi, JwtSecret jwtSecret) {
        this.OAuthSecret = OAuthSecret;
        this.githubApi = githubApi;
        this.jwtSecret = jwtSecret;
    }

    @Override
    public void run(String... args) {
        log.info("Start Airbnb initializer");
        log.info("serverSecret : {}", OAuthSecret);
        log.info("githubApi : {}", githubApi);
        log.info("jwtSecret : {}", jwtSecret);
    }
}
