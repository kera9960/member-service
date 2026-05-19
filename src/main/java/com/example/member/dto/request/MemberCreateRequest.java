package com.example.member.dto.request;

import com.example.member.enums.Mbti;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotNull(message = "나이는 필수입니다.")
    @Min(value = 1, message = "나이는 1 이상이어야 합니다.")
    private Integer age;
    @NotNull(message = "MBTI는 필수입니다.")
    private Mbti mbti;
}
