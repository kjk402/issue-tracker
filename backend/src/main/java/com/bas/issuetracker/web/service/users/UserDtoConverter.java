package com.bas.issuetracker.web.service.users;

import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.dto.UserWithToken;
import org.springframework.stereotype.Service;

@Service
public class UserDtoConverter {
    public UserWithToken userToUserWithToken(User user) {
        return UserWithToken.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .accessToken(user.getAccessToken())
                .build();
    }
}
