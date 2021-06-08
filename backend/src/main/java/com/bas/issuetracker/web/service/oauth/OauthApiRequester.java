package com.bas.issuetracker.web.service.oauth;

import com.bas.issuetracker.web.domain.user.User;

public interface OauthApiRequester {
    String accessToken(String code, String clientId, String clientSecret);

    User profile(String accessToken);
}
