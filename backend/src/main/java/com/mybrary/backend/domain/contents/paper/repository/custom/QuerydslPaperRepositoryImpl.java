package com.mybrary.backend.domain.contents.paper.repository.custom;

import static com.mybrary.backend.domain.contents.like.entity.QLike.like;
import static com.mybrary.backend.domain.contents.paper.entity.QPaper.paper;
import static com.mybrary.backend.domain.image.entity.QImage.image;

import com.mybrary.backend.domain.contents.paper.dto.GetFollowingPaperDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;

@RequiredArgsConstructor
public class QuerydslPaperRepositoryImpl  implements QuerydslPaperRepositoryCustom {
    private final JPAQueryFactory query;

    List<GetFollowingPaperDto> getFollowingPaperDtosResults(Long threadid){
        /* 썸네일주소 1, 2를 바로 받을수 있는 방법? */
        return query.select(Projections.constructor(GetFollowingPaperDto.class, paper.id, paper.layoutType, paper.content1, paper.content2, image.originUrl, like,))
    }
}
