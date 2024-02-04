package com.mybrary.backend.domain.contents.likes.repository.custom;

import com.mybrary.backend.domain.contents.likes.entity.Likes;
import java.util.Optional;

public interface LikesRepositoryCustom {
    Optional<Likes> isLikedPaper(Long paperId, Long memberId);
}
