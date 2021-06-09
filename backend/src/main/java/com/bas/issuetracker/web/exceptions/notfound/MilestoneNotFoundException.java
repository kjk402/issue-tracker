package com.bas.issuetracker.web.exceptions.notfound;

public class MilestoneNotFoundException extends NotFoundException {
    public static final String MILESTONE_NOT_FOUND = "마일스톤을 찾을 수 없습니다 id : ";

    public MilestoneNotFoundException(String message, int id) {
        super(message + id);
    }
}
