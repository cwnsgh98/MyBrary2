package com.mybrary.backend.domain.chat.repository.custom;

import static com.mybrary.backend.domain.chat.entity.QChatMessage.chatMessage;
import static com.mybrary.backend.domain.chat.entity.QChatRoom.chatRoom;
import static com.mybrary.backend.domain.image.entity.QImage.image;
import static com.mybrary.backend.domain.member.entity.QMember.member;

import com.mybrary.backend.domain.chat.dto.ChatMessageResponseDto;
import com.mybrary.backend.domain.member.dto.login.MemberInfo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Page<ChatMessageResponseDto> fetchChatMessagesByRoomId(Long chatRoomId, Pageable pageable) {
        var content = query.select(getChatMessageResponseDto())
                           .from(chatMessage)
                           .leftJoin(chatRoom).on(chatMessage.chatRoom.id.eq(chatRoom.id))
                           .leftJoin(member).on(chatMessage.sender.id.eq(member.id))
                           .leftJoin(image).on(member.profileImage.id.eq(image.id))
                           .where(chatMessage.chatRoom.id.eq(chatRoomId))
                           .offset(pageable.getOffset())
                           .limit(pageable.getPageSize())
                           .orderBy(chatMessage.createdAt.asc())
                           .fetch();

        JPAQuery<Long> countQuery = query.select(chatMessage.count())
                                         .from(chatMessage)
                                         .leftJoin(chatRoom).on(chatMessage.chatRoom.id.eq(chatRoom.id))
                                         .leftJoin(member).on(chatMessage.sender.id.eq(member.id))
                                         .leftJoin(image).on(member.profileImage.id.eq(image.id))
                                         .where(chatMessage.chatRoom.id.eq(chatRoomId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private static QBean<ChatMessageResponseDto> getChatMessageResponseDto() {
        return Projections.fields(ChatMessageResponseDto.class,
                                  chatMessage.sender.id.as("sender_id"),
                                  chatMessage.receiver.id.as("receiverId"),
                                  chatMessage.message.as("message"),
                                  Projections.fields(MemberInfo.class,
                                                     member.id.as("memberId"),
                                                     member.email.as("email"),
                                                     member.name.as("name"),
                                                     member.nickname.as("nickname"),
                                                     image.url.as("profileImageUrl")
                                  ));
    }

}
