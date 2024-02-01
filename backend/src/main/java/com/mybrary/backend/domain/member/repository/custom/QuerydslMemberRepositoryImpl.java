package com.mybrary.backend.domain.member.repository.custom;

import static com.mybrary.backend.domain.member.entity.QMember.member;
import static com.mybrary.backend.domain.follow.entity.QFollow.follow;

import com.mybrary.backend.domain.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuerydslMemberRepositoryImpl implements QuerydslMemberRepository {

    private final JPAQueryFactory query;

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(query.selectFrom(member)
                                        .where(member.email.eq(email))
                                        .fetchFirst());
    }


}
