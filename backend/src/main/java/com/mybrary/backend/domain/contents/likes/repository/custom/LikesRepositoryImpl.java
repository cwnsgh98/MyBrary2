package com.mybrary.backend.domain.contents.likes.repository.custom;

import static com.mybrary.backend.domain.contents.likes.entity.QLikes.likes;
import static com.mybrary.backend.domain.member.entity.QMember.member;

import com.mybrary.backend.domain.contents.likes.entity.Likes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LikesRepositoryImpl implements LikesRepositoryCustom {

    private final JPAQueryFactory query;

    public Optional<Likes> isLikedPaper(Long paperId, Long memberId) {
        return Optional.ofNullable(query.select(likes)
            .from(likes)
            .where(likes.member.id.eq(memberId).and(likes.paper.id.eq(paperId)))
            .fetchOne());
    }

    ;
}
