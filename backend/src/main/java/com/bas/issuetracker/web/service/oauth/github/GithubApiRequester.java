package com.bas.issuetracker.web.service.oauth.github;

import com.bas.issuetracker.web.config.properties.GithubApi;
import com.bas.issuetracker.web.config.properties.OAuthSecret;
import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.dto.GithubProfile;
import com.bas.issuetracker.web.dto.ReceivedAccessToken;
import com.bas.issuetracker.web.dto.RequestAccessToken;
import com.bas.issuetracker.web.service.oauth.ApiRequester;
import com.bas.issuetracker.web.service.oauth.OauthApiRequester;
import com.bas.issuetracker.web.service.oauth.OauthProfileConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GithubApiRequester implements OauthApiRequester {

    private final GithubApi githubApi;
    private final OauthProfileConverter oauthProfileConverter;
    private final ApiRequester apiRequester;

    public GithubApiRequester(GithubApi githubApi,
                              OauthProfileConverter oauthProfileConverter,
                              ApiRequester apiRequester) {
        this.githubApi = githubApi;
        this.oauthProfileConverter = oauthProfileConverter;
        this.apiRequester = apiRequester;
    }

    @Override
    public String accessToken(String code, String clientId, String clientSecret) {
        RequestAccessToken requestAccessToken = RequestAccessToken.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(code)
                .build();
        ReceivedAccessToken receivedAccessToken = apiRequester.callApi(githubApi.getAccessTokenUrl(), HttpMethod.POST,
                new HttpHeaders(), requestAccessToken, ReceivedAccessToken.class);
        return receivedAccessToken.getAccessToken();
    }

    @Override
    public User profile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        GithubProfile githubProfile = apiRequester.callApi(githubApi.getProfileUrl(), HttpMethod.GET, headers,
                HttpEntity.EMPTY, GithubProfile.class);
        return oauthProfileConverter.githubProfileToUser(githubProfile);
    }
}
