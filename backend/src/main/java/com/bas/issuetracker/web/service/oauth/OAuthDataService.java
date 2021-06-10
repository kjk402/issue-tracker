package com.bas.issuetracker.web.service.oauth;

import com.bas.issuetracker.web.config.properties.GithubApi;
import com.bas.issuetracker.web.config.properties.OAuthSecret;
import com.bas.issuetracker.web.dto.OAuthLoginData;
import org.springframework.stereotype.Service;

@Service
public class OAuthDataService {
    private final GithubApi githubApi;
    private final OAuthSecret oauthSecret;

    public OAuthDataService(GithubApi githubApi, OAuthSecret oauthSecret) {
        this.githubApi = githubApi;
        this.oauthSecret = oauthSecret;
    }

    public String githubFeLoginUrl() {
        return githubApi.getLoginUrl() +
                "?" +
                oauthSecret.getClientIdKey() +
                "=" +
                oauthSecret.getFeClientIdValue() +
                "&" +
                githubApi.getScopeKey() +
                "=" +
                githubApi.getScopeValue();
    }

    public OAuthLoginData showGithubOAuthData() {
        return new OAuthLoginData(oauthSecret.getIosClientIdValue(), githubApi.getScopeValue());
    }
}
