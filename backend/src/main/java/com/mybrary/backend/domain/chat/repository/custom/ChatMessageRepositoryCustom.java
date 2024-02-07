package com.mybrary.backend.domain.chat.repository.custom;

import com.mybrary.backend.domain.chat.dto.ChatMessageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatMessageRepositoryCustom {

    Page<ChatMessageResponseDto> fetchChatMessagesByRoomId(Long chatRoomId, Pageable pageable);
}
