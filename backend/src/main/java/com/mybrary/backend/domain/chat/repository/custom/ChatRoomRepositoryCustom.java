package com.mybrary.backend.domain.chat.repository.custom;

import com.mybrary.backend.domain.chat.dto.ChatRoomGetDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ChatRoomRepositoryCustom {

    List<Long> chatRoomIdList2(Long myId, Pageable page);
}