package com.bas.issuetracker.web.exceptions;

public class IssueException extends RuntimeException{
    public IssueException() {
    }

    public IssueException(String message) {
        super(message);
    }

}
