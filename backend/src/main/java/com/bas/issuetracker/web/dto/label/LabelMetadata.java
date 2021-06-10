package com.bas.issuetracker.web.dto.label;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LabelMetadata {

    @NotBlank(message = "라벨 제목은 비어있으면 안됩니다")
    @Length(max = 50, message = "라벨 제목의 길이는 {max}를 넘을 수 없습니다")
    private String title;

    @NotBlank(message = "라벨 설명은 비어있으면 안됩니다")
    @Length(max = 300, message = "라벨 설명의 길이는 {max}를 넘을 수 없습니다")
    private String description;

    @NotNull(message = "라벨 색상은 비어있으면 안됩니다")
    private String color;
}
