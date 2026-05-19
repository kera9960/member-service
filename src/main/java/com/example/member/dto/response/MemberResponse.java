package com.example.member.dto.response;

import com.example.member.entity.Member;
import com.example.member.enums.Mbti;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResponse {

    private final Long id;
    private final String name;
    private final Integer age;
    private final Mbti mbti;

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
                );
    }

}
