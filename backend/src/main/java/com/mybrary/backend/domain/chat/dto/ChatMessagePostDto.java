package com.mybrary.backend.domain.chat.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessagePostDto {

    private Long chatRoomId;
    private Long senderId;
    private Long threadId;
    private String message;
}
