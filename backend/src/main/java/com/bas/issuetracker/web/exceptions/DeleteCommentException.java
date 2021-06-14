package com.bas.issuetracker.web.exceptions;

public class DeleteCommentException extends RuntimeException{
    public DeleteCommentException() {
    }

    public DeleteCommentException(String message) {
        super(message);
    }

}
