package com.bas.issuetracker.web.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private int id;
    private String nickname;
    private String name;
    private String profileImage;
    private String accessToken;
    private String oauthId;
    private OAuthAuthenticater authenticatedBy;

    public void updateToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void updateId(int id) {
        this.id = id;
    }
}
