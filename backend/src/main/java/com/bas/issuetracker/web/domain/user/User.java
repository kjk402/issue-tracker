package com.bas.issuetracker.web.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class User {
    @Id
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
}
