package com.example.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProfileImageUrlResponse {

    private final String presignedUrl;
    private final LocalDateTime expiresAt;
}
