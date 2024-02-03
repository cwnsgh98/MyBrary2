package com.mybrary.backend.domain.contents.paper.repository.custom;

import com.mybrary.backend.domain.contents.like.entity.Like;
import com.mybrary.backend.domain.contents.paper.dto.GetFollowingPaperDto;
import java.util.List;
import java.util.Optional;

public interface PaperRepositoryCustom {

    List<GetFollowingPaperDto> getFollowingPaperDtoResults(Long threadId);

    Optional<Like> isLikedPaper(Long paperId, Long memberId);
}
