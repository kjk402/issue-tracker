package com.bas.issuetracker.web.controller;

import com.bas.issuetracker.web.config.annotation.UserAgent;
import com.bas.issuetracker.web.config.properties.OAuthSecret;
import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.domain.user.UserAgentEnum;
import com.bas.issuetracker.web.dto.OAuthLoginData;
import com.bas.issuetracker.web.dto.UserWithToken;
import com.bas.issuetracker.web.service.oauth.OAuthDataService;
import com.bas.issuetracker.web.service.oauth.OauthApiRequester;
import com.bas.issuetracker.web.service.users.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Api(tags = {"User API"})
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
    @ApiOperation(value = "OAuth callback", notes = "OAuth 로그인 플로우의 콜백을 처리합니다.")
    public UserWithToken githubCallback(@ApiParam(value = "OAuth code") @RequestParam(value = "code") String code,
                                        @UserAgent UserAgentEnum userAgent) {
        String clientId = oAuthSecret.clientId(userAgent);
        String clientSecret = oAuthSecret.clientSecret(userAgent);
        String githubAccessToken = githubApiRequester.accessToken(code, clientId, clientSecret);
        User user = githubApiRequester.profile(githubAccessToken);
        return userService.processLogin(user);
    }

    @GetMapping
    public OAuthLoginData showOAuthData(@UserAgent UserAgentEnum userAgent) {
        return oauthDataService.showGithubOAuthData(userAgent);
    }
}
