package com.bas.issuetracker.web.exceptions.notfound;

public class LabelNotFoundException extends NotFoundException {
    public static final String LABEL_NOT_FOUND = "라벨을 찾을 수 없습니다 id : ";

    public LabelNotFoundException(String message, int id) {
        super(message + id);
    }
}
