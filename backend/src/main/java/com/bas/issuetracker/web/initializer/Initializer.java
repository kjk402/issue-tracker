package com.bas.issuetracker.web.initializer;

import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.service.oauth.TokenService;
import com.bas.issuetracker.web.service.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Initializer implements CommandLineRunner {

    private final UserService userService;
    private final TokenService tokenService;

    public Initializer(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    public void run(String... args) throws Exception {
        String token = tokenService.createTesterToken(1);
        User user = userService.findUserById(1);
        userService.updateWithNewToken(user, token);
        log.info("admin id : {}", user.getId());
        log.info("admin token : {}", token);
    }
}
