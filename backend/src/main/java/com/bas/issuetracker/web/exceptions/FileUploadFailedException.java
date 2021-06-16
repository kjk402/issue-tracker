package com.bas.issuetracker.web.exceptions;

public class FileUploadFailedException extends RuntimeException {
    public static final String NO_FILE_NAME = "파일의 이름을 읽을 수 없습니다";
    public static final String INVALID_FILE_NAME = "파일의 확장자를 읽을 수 없습니다";
    public static final String INVALID_FILE_EXTENTION = "허용되지 않는 확장자입니다";

    public FileUploadFailedException(String message) {
        super(message);
    }
}
