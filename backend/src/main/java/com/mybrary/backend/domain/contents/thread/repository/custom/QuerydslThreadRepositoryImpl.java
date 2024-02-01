package com.mybrary.backend.domain.contents.thread.repository.custom;


import static com.mybrary.backend.domain.book.entity.QBook.book;
import static com.mybrary.backend.domain.contents.paper.entity.QPaper.paper;
import static com.mybrary.backend.domain.contents.scrap.entity.QScrap.scrap;
import static com.mybrary.backend.domain.contents.thread.entity.QThreads.threads;
import static com.mybrary.backend.domain.follow.entity.QFollow.follow;
import static com.mybrary.backend.domain.member.entity.QMember.member;
import static com.mybrary.backend.domain.mybrary.entity.QMybrary.mybrary;

import com.mybrary.backend.domain.contents.thread.dto.GetFollowingThreadDto;
import com.mybrary.backend.domain.contents.thread.entity.Threads;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuerydslThreadRepositoryImpl implements QuerydslThreadRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<Threads> findByThreadId(Long threadId) {
        return Optional.ofNullable(query.selectFrom(threads)
                                        .where(threads.id.eq(threadId))
                                        .fetchFirst());
        }

    /* 쓰레드 기본 정보와 작성자 정보들 관련 dto, threads 날짜순 정렬 -> 헤딩 thread별 paper 관련정보 조회 예정 */
    /* 이게 맞나.. */

    public List<GetFollowingThreadDto> getFollowingThreadDtoResults(Long memberId){
        return query.select(Projections.constructor(GetFollowingThreadDto.class, book.id, threads.id, threads.createdAt, member.id, member.nickname, member.profileImage))
            .from(follow)
            .where(follow.follower.id.eq(memberId))
//            .innerJoin(follow.following, mybrary.member)
            .innerJoin(mybrary)
            .innerJoin(threads)
//            .innerJoin(paper)
            .innerJoin(scrap)
            .orderBy(threads.createdAt.desc())
            .groupBy(threads)
            .fetch();


    }

}
