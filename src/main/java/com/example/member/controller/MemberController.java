package com.example.member.controller;

import com.example.member.dto.request.MemberCreateRequest;
import com.example.member.dto.response.MemberResponse;
import com.example.member.dto.response.ProfileImageUrlResponse;
import com.example.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<MemberResponse> createMember(
           @Valid @RequestBody MemberCreateRequest memberCreateRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberCreateRequest));
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<MemberResponse> getMember(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMember(memberId));
    }

    @PostMapping("/api/members/{memberId}/profile-image")
    public ResponseEntity<Void> uploadImage(
            @PathVariable Long memberId
            , @RequestParam("file") MultipartFile file
    ) {
        memberService.uploadProfileImage(memberId,file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/members/{memberId}/profile-image")
    public ResponseEntity<ProfileImageUrlResponse> getProfileImageUrl(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getProfileImageUrl(memberId));
    }
}
