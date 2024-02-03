package com.mybrary.backend.domain.contents.like.repository.custom;

import static com.mybrary.backend.domain.contents.like.entity.QLike.like;
import static com.mybrary.backend.domain.contents.paper.entity.QPaper.paper;

import com.mybrary.backend.domain.contents.like.entity.Like;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepositoryCustom{

    private final JPAQueryFactory query;

    /* 페이퍼에 대한 좋아요 여부 반환 */
    public Optional<Like> isLikedPaper(Long paperId, Long memberId) {
        return Optional.ofNullable(
            query.selectFrom(like)
                .leftJoin(paper).on(like.paper.id.eq(paper.id))
                .where(paper.id.eq(paperId).and(like.member.id.eq(memberId)))
                .fetchFirst());
    }

}
