package com.mybrary.backend.domain.mybrary.repository.custom;

import static com.mybrary.backend.domain.mybrary.entity.QMybrary.mybrary;

import com.mybrary.backend.domain.mybrary.entity.Mybrary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuerydslMybraryRepositoryImpl implements QuerydslMybraryRepository{

    private final JPAQueryFactory query;

    @Override
    public Mybrary findByMybraryId(Long mybraryId) {
        return query.selectFrom(mybrary)
            .where(mybrary.id.eq(mybraryId))
            .fetchFirst();
    }


}
