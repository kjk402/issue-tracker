package com.bas.issuetracker.web.service.oauth;

import com.bas.issuetracker.web.config.constants.UserConstants;
import com.bas.issuetracker.web.domain.user.OAuthAuthenticater;
import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.dto.GithubProfile;
import org.springframework.stereotype.Service;

@Service
public class OauthProfileConverter {

    public User githubProfileToUser(GithubProfile githubProfile) {
        return User.builder()
                .id(UserConstants.ID_OF_NO_USER)
                .nickname(githubProfile.getLogin())
                .name(githubProfile.getName())
                .profileImage(githubProfile.getAvatarUrl())
                .authenticatedBy(OAuthAuthenticater.GITHUB)
                .oauthId(githubProfile.getId())
                .build();
    }
}
