package com.bas.issuetracker.web.exceptions.notfound;

public class UserNotFoundException extends NotFoundException {
    public static final String USER_NOT_FOUND = "유저를 찾을 수 없습니다 id : ";

    public UserNotFoundException(String message, int guestId) {
        super(message + guestId);
    }
}
