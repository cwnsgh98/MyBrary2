package com.mybrary.backend.domain.chat.service;

import com.mybrary.backend.domain.chat.dto.ChatMessageResponseDto;
import com.mybrary.backend.domain.chat.dto.ChatMessagePostDto;
import com.mybrary.backend.domain.chat.dto.ChatRoomGetDto;
import com.mybrary.backend.domain.chat.dto.TChatMessageGetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ChatService {


    Long saveChatMessage(ChatMessagePostDto chatMessagePostDto);

    List<ChatRoomGetDto> getAllChatRoom(String email, Pageable page);

    void deleteChatRoom(String email, Long chatRoomId);

    List<TChatMessageGetDto> getAllChatByChatRoomId(String email, Long chatRoomId, Pageable page);

    List<TChatMessageGetDto> getAllChatByMemberId(String email, Long memberId, Pageable page);

    Long createChat(String email, ChatMessagePostDto message);

    Long threadShare(String email, ChatMessagePostDto message);

    Page<ChatMessageResponseDto> fetchMessageListByChatRoomId(Long chatRoomId, Pageable pageable);
}