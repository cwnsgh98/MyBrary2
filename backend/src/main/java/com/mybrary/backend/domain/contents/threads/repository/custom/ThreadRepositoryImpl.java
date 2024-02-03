package com.mybrary.backend.domain.contents.threads.repository.custom;


import static com.mybrary.backend.domain.contents.paper.entity.QPaper.paper;
import static com.mybrary.backend.domain.contents.scrap.entity.QScrap.scrap;
import static com.mybrary.backend.domain.contents.threads.entity.QThreads.threads;
import static com.mybrary.backend.domain.follow.entity.QFollow.follow;
import static com.mybrary.backend.domain.member.entity.QMember.member;
import static com.mybrary.backend.domain.mybrary.entity.QMybrary.mybrary;
import com.mybrary.backend.domain.contents.threads.dto.GetFollowingThreadDto;
import com.mybrary.backend.domain.contents.threads.entity.Threads;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
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


    /* 쓰레드 기본 정보와 작성자 정보들 관련 dto, threads 날짜순 정렬 -> 헤딩 thread별 paper 관련정보 조회 예정 */
    /* 이게 맞나.. */

    public List<GetFollowingThreadDto> getFollowingThreadDtoResults(Long memberId, Pageable pageable){
        return query.select(
                Projections.constructor(GetFollowingThreadDto.class, scrap.book.id, threads.id,
                    threads.createdAt, member.id, member.name, member.nickname,
                    member.profileImage.thumbnailUrl))
            .from(threads)
            .leftJoin(paper).on(paper.threads.id.eq(threads.id))
            .leftJoin(scrap).on(scrap.paper.id.eq(paper.id))
            .leftJoin(mybrary).on(mybrary.id.eq(threads.id))
            .leftJoin(member).on(member.id.eq(mybrary.id))
            .leftJoin(follow).on(follow.following.id.eq(member.id))
            .where(follow.follower.id.eq(memberId))
            .groupBy(threads.id)
            .orderBy(threads.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    }
//            .from(follow)
//            .where(follow.follower.id.eq(memberId))
//            .innerJoin(mybrary)
//            .innerJoin(member)
//            .innerJoin(threads)
//            .innerJoin(paper)
//            .innerJoin(scrap)
//            .orderBy(threads.createdAt.desc())
//            .groupBy(threads)
//            .limit(5)
//            .fetch();





//    @Override
//    public List<GetFollowingThreadDto> getRandomThreadDtoResults(Long memberId, int count) {
//        return query.select(Projections.constructor(GetFollowingThreadDto.class, book.id, threads.id, threads.createdAt, member.id, member.name, member.nickname, member.profileImage.thumbnailUrl))
//            .innerJoin(mybrary)
//            .innerJoin(threads)
//            .innerJoin(scrap)
//            .orderBy(threads.createdAt.desc())
//            .groupBy(threads)
//            .limit(count)
//            .fetch();
//    }


}
