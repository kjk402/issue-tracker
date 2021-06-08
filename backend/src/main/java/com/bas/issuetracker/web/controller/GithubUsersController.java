package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.config.annotation.CertifiedUser;
import com.bas.issuetracker.web.config.annotation.UserAgent;
import com.bas.issuetracker.web.config.properties.OAuthSecret;
import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.domain.user.UserAgentEnum;
import com.bas.issuetracker.web.dto.UserWithToken;
import com.bas.issuetracker.web.service.oauth.OAuthDataService;
import com.bas.issuetracker.web.service.oauth.OauthApiRequester;
import com.bas.issuetracker.web.service.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequestMapping("/users/github")
@RestController
public class GithubUsersController {

    private final OAuthDataService oauthDataService;
    private final OauthApiRequester githubApiRequester;
    private final UserService userService;
    private final OAuthSecret oAuthSecret;

    public GithubUsersController(OAuthDataService oauthDataService,
                                 OauthApiRequester githubApiRequester,
                                 UserService userService,
                                 OAuthSecret oAuthSecret) {
        this.oauthDataService = oauthDataService;
        this.githubApiRequester = githubApiRequester;
        this.userService = userService;
        this.oAuthSecret = oAuthSecret;
    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect(oauthDataService.githubFeLoginUrl());
    }

    @GetMapping("/callback")
    public UserWithToken githubCallback(@RequestParam(value = "code") String code,
                                        @UserAgent UserAgentEnum userAgent) {
        String clientId = oAuthSecret.clientId(userAgent);
        String clientSecret = oAuthSecret.clientSecret(userAgent);
        String githubAccessToken = githubApiRequester.accessToken(code, clientId, clientSecret);
        User user = githubApiRequester.profile(githubAccessToken);
        return userService.processLogin(user);
    }

    @GetMapping("/test")
    public UserWithToken testTokenCheck(@CertifiedUser int userId) {
        userService
    }
}