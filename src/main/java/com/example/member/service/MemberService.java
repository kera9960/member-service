package com.example.member.service;

import com.example.common.exception.InvalidImageFileException;
import com.example.common.exception.MemberNotFoundException;
import com.example.member.dto.request.MemberCreateRequest;
import com.example.member.dto.response.MemberResponse;
import com.example.member.dto.response.ProfileImageUrlResponse;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;

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

    @Transactional
    public void uploadProfileImage(Long memberId, MultipartFile file) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 멤버입니다.")
        );

        String key = s3Service.uploadProfileImage(file);

        member.updateProfileImageKey(key);
    }

    @Transactional(readOnly = true)
    public ProfileImageUrlResponse getProfileImageUrl(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException("존재하지 않는 멤버입니다.")
        );

        String key = member.getProfileImageKey();

        if (key == null || key.isBlank()) {
            throw new InvalidImageFileException("키가 없습니다.");
        }

        return s3Service.createPresignedUrl(key);
    }
}
