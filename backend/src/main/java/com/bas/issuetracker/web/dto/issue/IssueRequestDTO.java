package com.bas.issuetracker.web.dto.issue;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class IssueRequestDTO {
    @NotBlank(message = "이슈의 제목은 비어있으면 안됩니다")
    @Length(max = 50, message = "이슈 제목의 길이는 50글자 이하여야 합니다")
    private String title;

    @NotBlank(message = "이슈의 내용은 비어있으면 안됩니다")
    @Length(max = 50, message = "이슈 내용의 길이는 1000글자 이하여야 합니다")
    private String comment;

    @NotNull(message = "마일스톤 아이디 목록은 Null값이면 안됩니다. 비어있더라도 빈 배열을 보내야 합니다")
    private List<Integer> milestoneId;

    @NotNull(message = "레이블 아이디 목록은 Null값이면 안됩니다. 비어있더라도 빈 배열을 보내야 합니다")
    private List<Integer> labelIds;

    @NotNull(message = "Assigned User의 아이디 목록은 Null값이면 안됩니다. 비어있더라도 빈 배열을 보내야 합니다")
    private List<Integer> assignedUserIds;

    @NotNull(message = "이미지의 아이디 목록은 Null값이면 안됩니다. 비어있더라도 빈 배열을 보내야 합니다")
    private List<Integer> imageIds;

    public IssueRequestDTO(String title, String comment, List<Integer> milestoneId, List<Integer> labelIds, List<Integer> assignedUserIds, List<Integer> imageIds) {
        this.title = title;
        this.comment = comment;
        this.milestoneId = milestoneId;
        this.labelIds = labelIds;
        this.assignedUserIds = assignedUserIds;
        this.imageIds = imageIds;
    }

    protected IssueRequestDTO() {
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public Integer getMilestoneId() {
        if (milestoneId.isEmpty()) {
            return null;
        }
        return milestoneId.get(0);
    }

    public List<Integer> getLabelIds() {
        return labelIds;
    }

    public List<Integer> getAssignedUserIds() {
        return assignedUserIds;
    }

    public List<Integer> getImageIds() {
        return imageIds;
    }
}

