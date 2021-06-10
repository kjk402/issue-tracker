package com.bas.issuetracker.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneMetadata {

    @NotBlank(message = "마일스톤 제목은 비어있으면 안됩니다")
    @Length(max = 50, message = "마일스톤 제목의 길이는 {max}를 넘을 수 없습니다")
    private String title;

    @NotBlank(message = "마일스톤 설명은 비어있으면 안됩니다")
    @Length(max = 300, message = "마일스톤 설명의 길이는 {max}를 넘을 수 없습니다")
    private String description;

    @NotNull(message = "마일스톤 기한은 비어있으면 안됩니다")
    @FutureOrPresent(message = "마일스톤 기한은 현재 또는 미래여야 합니다")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueToDate;
}
