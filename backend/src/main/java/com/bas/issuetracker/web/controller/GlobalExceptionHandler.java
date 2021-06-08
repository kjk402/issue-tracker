package com.bas.issuetracker.web.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bas.issuetracker.web.config.constants.GlobalErrorMessageConstants;
import com.bas.issuetracker.web.dto.ErrorResponse;
import com.bas.issuetracker.web.exceptions.jwt.JwtTokenException;
import com.bas.issuetracker.web.exceptions.notfound.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleMethodNotAllowed(HttpRequestMethodNotSupportedException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtTokenException.class)
    public ErrorResponse handleNoJwtTokenException(JwtTokenException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JWTVerificationException.class)
    public ErrorResponse handleJWTVerificationException(JWTVerificationException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleUnknownError(Exception exception) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        return new ErrorResponse(GlobalErrorMessageConstants.ERROR_OCCURED_FROM_SERVER);
    }
}
