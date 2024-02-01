package com.mybrary.backend.domain.contents.thread.repository.custom;

import static com.mybrary.backend.domain.contents.thread.entity.QThreads.threads;

import com.mybrary.backend.domain.contents.thread.entity.Threads;
import com.mybrary.backend.domain.member.entity.Member;
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




}
