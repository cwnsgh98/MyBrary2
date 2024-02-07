package com.mybrary.backend.domain.chat.dto;

import com.mybrary.backend.domain.member.dto.login.MemberInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageResponseDto {

    private Long senderId;
    private Long receiverId;
    private String message;
    private MemberInfo memberInfo;
}
