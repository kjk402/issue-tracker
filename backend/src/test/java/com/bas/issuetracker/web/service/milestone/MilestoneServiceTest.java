package com.bas.issuetracker.web.service.milestone;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.dto.MilestoneMetadata;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MilestoneServiceTest {

    @Autowired
    private MilestoneService milestoneService;

    @Test
    @DisplayName("마일스톤을 저장할 수 있어야 합니다")
    void testSaveMilestone() {
        Milestone milestone = createMilestone();
        Milestone foundMilestone = milestoneService.findMilestone(milestone.getId());

        assertThat(milestone)
                .extracting(
                        Milestone::getId,
                        Milestone::getTitle,
                        Milestone::getDescription,
                        Milestone::getLastModifiedDateTime,
                        Milestone::getDueToDate,
                        Milestone::isOpen
                )
                .doesNotContainNull()
                .containsExactly(
                        foundMilestone.getId(),
                        foundMilestone.getTitle(),
                        foundMilestone.getDescription(),
                        foundMilestone.getLastModifiedDateTime(),
                        foundMilestone.getDueToDate(),
                        foundMilestone.isOpen()
                );
    }

    @Test
    @DisplayName("마일스톤의 메타데이터를 업데이트할 수 있어야 합니다")
    void testUpdateMetadata() {
        Milestone milestone = createMilestone();
        MilestoneMetadata metadata = createMilestoneMetadata();
        milestoneService.updateMilestone(milestone.getId(), metadata);
        Milestone updatedMilestone = milestoneService.findMilestone(milestone.getId());
        assertThat(updatedMilestone)
                .extracting(
                        Milestone::getTitle,
                        Milestone::getDescription,
                        Milestone::getDueToDate
                )
                .doesNotContainNull()
                .containsExactly(
                        metadata.getTitle(),
                        metadata.getDescription(),
                        metadata.getDueToDate()
                );
    }

    @Test
    @DisplayName("마일스톤을 생성하면 open상태여야 합니다")
    void testInitailOpenState() {
        Milestone milestone = createMilestone();
        assertThat(milestone.isOpen()).isTrue();
    }

    @Test
    @DisplayName("마일스톤을 닫을 수 있어야 합니다")
    void testCloseMilestone() {
        Milestone milestone = createMilestone();
        milestoneService.closeMilestone(milestone.getId());
        Milestone closedMilestone = milestoneService.findMilestone(milestone.getId());
        assertThat(closedMilestone.isOpen()).isFalse();
    }

    @Test
    @DisplayName("마일스톤을 다시 열 수 있어야 합니다")
    void testReOpenMilestone() {
        Milestone milestone = createMilestone();
        milestoneService.closeMilestone(milestone.getId());
        milestoneService.openMilestone(milestone.getId());
        Milestone reopenedMilestone = milestoneService.findMilestone(milestone.getId());
        assertThat(reopenedMilestone.isOpen()).isTrue();
    }

    private Milestone createMilestone() {
        MilestoneMetadata metadata = createMilestoneMetadata();
        return milestoneService.createMilestone(metadata);
    }

    private MilestoneMetadata createMilestoneMetadata() {
        return MilestoneMetadata.builder()
                .description("updatedDescription")
                .title("updatedTitle")
                .dueToDate(LocalDate.of(2021, 10, 1))
                .build();
    }
}
