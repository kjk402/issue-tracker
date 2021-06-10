package com.bas.issuetracker.web.dto.milestone;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
public class MilestoneState {

    @NotNull(message = "마일스톤 상태정보는 비어있으면 안됩니다")
    private boolean opened;

    public boolean isOpen() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
