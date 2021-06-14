package com.bas.issuetracker.web.dto.issue;

public class UserDTO {
    private String nickname;
    private String name;
    private String profileImage;

    public UserDTO(String nickname, String name, String profileImage) {
        this.nickname = nickname;
        this.name = name;
        this.profileImage = profileImage;
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
