package com.bas.issuetracker.web.service.milestone;

import com.bas.issuetracker.web.domain.milestone.Milestone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MilestoneServiceTest {

    @Autowired
    private MilestoneService milestoneService;

    @Test
    @DisplayName("마일스톤을 저장할 수 있어야 합니다")
    void testSaveMilestone() {

        Milestone milestone = Milestone.builder()
                .description("milestone description")
                .title("milestone title")
                .dueToDate(LocalDate.of(2021, 6, 1))
                .lastModifiedDateTime(LocalDateTime.of(2021, 6, 1, 12, 12))
                .build();
        milestoneService.saveMilestone(milestone);
        Milestone foundMilestone = milestoneService.findMilestone(milestone.getId());

        assertThat(milestone)
                .extracting(
                        Milestone::getId,
                        Milestone::getTitle,
                        Milestone::getDescription,
                        Milestone::getLastModifiedDateTime,
                        Milestone::getDueToDate
                )
                .doesNotContainNull()
                .containsExactly(
                        foundMilestone.getId(),
                        foundMilestone.getTitle(),
                        foundMilestone.getDescription(),
                        foundMilestone.getLastModifiedDateTime(),
                        foundMilestone.getDueToDate()
                );
    }
}
