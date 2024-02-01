package com.mybrary.backend.domain.mybrary.repository;

import com.mybrary.backend.domain.chat.entity.ChatJoin;
import com.mybrary.backend.domain.member.entity.Member;
import com.mybrary.backend.domain.mybrary.entity.Mybrary;
import com.mybrary.backend.domain.mybrary.repository.custom.QuerydslMybraryRepository;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MybraryRepository extends JpaRepository<Member, Long>, QuerydslMybraryRepository {
    @Query("select cj1 from ChatJoin cj1 inner join ChatJoin cj2 on cj1.chatRoom.id = cj2.chatRoom.id where cj1.joinMember.id = :myId and cj2.joinMember.id = :memberId")
    ChatJoin isExistChatRoom(@Param("myId") Long myId, @Param("memberId") Long memberId);

    @Query("select m.id FROM Mybrary m WHERE m.id IN :memberId")
    List<Long> findAllByMybraryIdByMemberId(List<Long> memberId);
}
