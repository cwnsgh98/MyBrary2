package com.mybrary.backend.domain.contents.paper.repository.custom;


import static com.mybrary.backend.domain.contents.like.entity.QLike.like;
import static com.mybrary.backend.domain.contents.paper.entity.QPaper.paper;
import static com.mybrary.backend.domain.contents.threads.entity.QThreads.threads;
import static com.mybrary.backend.domain.member.entity.QMember.member;

import com.mybrary.backend.domain.contents.like.entity.Like;
import com.mybrary.backend.domain.contents.paper.dto.GetFollowingPaperDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaperRepositoryImpl implements PaperRepositoryCustom {

    private final JPAQueryFactory query;

    public List<GetFollowingPaperDto> getFollowingPaperDtoResults(Long threadId) {
        /* 썸네일주소 1, 2를 바로 받을수 있는 방법? */
        return query.select(
                Projections.fields(GetFollowingPaperDto.class,
                    paper.id.as("id"),
                    paper.layoutType.as("layoutType"),
                    paper.content1.as("content1"),
                    paper.content2.as("content2"),
                    paper.likeCount.as("likeCount"),
                    paper.commentCount.as("commentCount"),
                    paper.scrapCount.as("scrapCount"),
                    paper.isScrapEnabled.as("isScrapEnabled")
                ))
            .from(paper)
            .leftJoin(threads).on(paper.threads.id.eq(threads.id))
            .where(threads.id.eq(threadId))
            .groupBy(threads)
            .fetch();
    }

    public Optional<Like> isLikedPaper(Long paperId, Long memberId){
        return Optional.ofNullable(query.select(like)
            .from(like)
            .leftJoin(member).on(member.id.eq(like.member.id))
            .where(like.member.id.eq(memberId).and(like.paper.id.eq(paperId)))
            .fetchOne());

    };

}
