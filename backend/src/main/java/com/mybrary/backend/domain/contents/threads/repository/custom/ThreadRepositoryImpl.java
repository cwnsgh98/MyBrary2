package com.mybrary.backend.domain.contents.threads.repository.custom;


import static com.mybrary.backend.domain.contents.paper.entity.QPaper.paper;
import static com.mybrary.backend.domain.contents.paperImage.entity.QPaperImage.paperImage;
import static com.mybrary.backend.domain.contents.scrap.entity.QScrap.scrap;
import static com.mybrary.backend.domain.contents.threads.entity.QThreads.threads;
import static com.mybrary.backend.domain.follow.entity.QFollow.follow;
import static com.mybrary.backend.domain.image.entity.QImage.image;
import static com.mybrary.backend.domain.member.entity.QMember.member;
import static com.mybrary.backend.domain.mybrary.entity.QMybrary.mybrary;
import static com.querydsl.jpa.JPAExpressions.select;

import com.mybrary.backend.domain.contents.threads.dto.GetThreadDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadInfoGetDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadSimpleGetDto;
import com.mybrary.backend.domain.contents.threads.entity.Threads;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ThreadRepositoryImpl implements ThreadRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<Threads> findByThreadId(Long threadId) {
        return Optional.ofNullable(query.selectFrom(threads)
            .where(threads.id.eq(threadId))
            .fetchFirst());
    }

    public List<GetThreadDto> getFollowingThreadDtoResults(Long memberId,
        Pageable pageable) {
        return query.select(
                Projections.constructor(GetThreadDto.class, scrap.book.id, threads.id,
                    threads.createdAt, member.id, member.name, member.nickname,
                    member.profileImage.imageUrl))
            .from(threads)
            .leftJoin(paper).on(paper.threads.id.eq(threads.id))
            .leftJoin(scrap).on(scrap.paper.id.eq(paper.id))
            .leftJoin(mybrary).on(mybrary.id.eq(threads.id))
            .leftJoin(member).on(member.id.eq(mybrary.id))
            .leftJoin(follow).on(follow.following.id.eq(member.id))
            .where(follow.follower.id.eq(memberId).or(member.id.eq(memberId)))
            .groupBy(threads.id)
            .orderBy(threads.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    }

    /* 나와 내가 팔로잉중인 회원을 제외한 회원들의 랜덤 쓰레드 n개 조회  */
    @Override
    public List<GetThreadDto> getRandomThreadDtoResults(Long memberId, Pageable pageable) {
        return query.select(
                Projections.constructor(GetThreadDto.class, scrap.book.id, threads.id,
                    threads.createdAt, member.id, member.name, member.nickname,
                    member.profileImage.imageUrl))
            .from(threads)
            .leftJoin(paper).on(paper.threads.id.eq(threads.id))
            .leftJoin(scrap).on(scrap.paper.id.eq(paper.id))
            .leftJoin(mybrary).on(mybrary.id.eq(threads.id))
            .leftJoin(member).on(member.id.eq(mybrary.id))
            .leftJoin(follow).on(follow.following.id.eq(member.id))
            .where(paper.member.id.ne(
                    select(follow.following.member.id)
                        .from(follow)
                        .where(follow.follower.member.id.eq(memberId)))
                    .and(paper.member.id.ne(memberId)))
            .groupBy(threads.id)
            .orderBy(threads.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    /* 내 쓰레드 조회하기, 특정 회원의 쓰레드 조회하기에 공통으로 사용됨 */
    @Override
    public List<ThreadInfoGetDto> getSimpleThreadDtoResults(Long memberId, Pageable pageable) {
        return query.select(
            Projections.constructor(ThreadInfoGetDto.class, threads.id,
                image.imageUrl, paper.likesCount, paper.commentCount, paper.scrapCount))
            .from(threads)
            .leftJoin(paper).on(paper.threads.id.eq(threads.id).and(paper.member.id.eq(memberId)))
            .leftJoin(paperImage).on(paper.id.eq(paperImage.paper.id).and(paperImage.imageSeq.eq(1)))
            .leftJoin(image).on(paperImage.paper.id.eq(paper.id))
            .groupBy(threads.id)
            .orderBy(threads.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }


}
