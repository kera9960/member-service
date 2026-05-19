package com.example.member.service;

import com.example.common.exception.MemberNotFoundException;
import com.example.member.dto.request.MemberCreateRequest;
import com.example.member.dto.response.MemberResponse;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse createMember(MemberCreateRequest memberCreateRequest) {
        Member member = new Member(
                memberCreateRequest.getName(),
                memberCreateRequest.getAge(),
                memberCreateRequest.getMbti()
        );

        Member savedMember = memberRepository.save(member);

        return MemberResponse.from(savedMember);
    }

    @Transactional(readOnly = true)
    public MemberResponse getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 멤버입니다.")
        );

        return MemberResponse.from(member);
    }
}
