package com.bas.issuetracker.web.exceptions.notfound;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
