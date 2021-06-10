package com.bas.issuetracker.web.service.label;

import com.bas.issuetracker.web.domain.label.Label;
import com.bas.issuetracker.web.domain.milestone.Milestone;
import com.bas.issuetracker.web.dto.label.LabelMetadata;
import com.bas.issuetracker.web.dto.milestone.MilestoneMetadata;
import com.bas.issuetracker.web.exceptions.notfound.LabelNotFoundException;
import com.bas.issuetracker.web.exceptions.notfound.MilestoneNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class LabelServiceTest {

    @Autowired
    private LabelService labelService;

    @Test
    @DisplayName("레이블을 저장할 수 있어야 합니다")
    void testSaveLabel() {
        Label label = createLabel();
        Label foundMilestone = labelService.findLabel(label.getId());

        assertThat(label)
                .extracting(
                        Label::getId,
                        Label::getTitle,
                        Label::getDescription,
                        Label::getColor
                )
                .doesNotContainNull()
                .containsExactly(
                        foundMilestone.getId(),
                        foundMilestone.getTitle(),
                        foundMilestone.getDescription(),
                        foundMilestone.getColor()
                );
    }

    @Test
    @DisplayName("레이블의 메타데이터를 업데이트할 수 있어야 합니다")
    void testUpdateMetadata() {
        Label label = createLabel();
        LabelMetadata metadata = createLabelMetadata();
        labelService.updateLabel(label.getId(), metadata);
        Label updatedLabel = labelService.findLabel(label.getId());
        assertThat(updatedLabel)
                .extracting(
                        Label::getTitle,
                        Label::getDescription,
                        Label::getColor
                )
                .doesNotContainNull()
                .containsExactly(
                        metadata.getTitle(),
                        metadata.getDescription(),
                        metadata.getColor()
                );
    }

    @Test
    @DisplayName("레이블을 삭제할 수 있어야 합니다")
    void testDeleteLabel() {
        Label label = createLabel();
        labelService.deleteLabel(label.getId());
        assertThatThrownBy(() -> labelService.findLabel(label.getId()))
                .isInstanceOf(LabelNotFoundException.class);
    }

    private Label createLabel() {
        LabelMetadata metadata = createLabelMetadata();
        return labelService.createLabel(metadata);
    }

    private LabelMetadata createLabelMetadata() {
        return LabelMetadata.builder()
                .description("updatedDescription")
                .title("updatedTitle")
                .color("#ffffff")
                .build();
    }
}
