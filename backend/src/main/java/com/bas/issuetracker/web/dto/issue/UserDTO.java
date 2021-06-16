package com.bas.issuetracker.web.dto.issue;

public class UserDTO {
    private int userId;
    private String nickname;
    private String name;
    private String profileImage;

    public UserDTO(int userId, String nickname, String name, String profileImage) {
        this.userId = userId;
        this.nickname = nickname;
        this.name = name;
        this.profileImage = profileImage;
    }

    public int getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

}
