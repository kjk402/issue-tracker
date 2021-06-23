package com.bas.issuetracker.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserWithToken {
    private int id;
    private String nickname;
    private String name;
    private String profileImage;
    private String email;
    private String accessToken;
}
