package com.mybrary.backend.domain.contents.likes.service;

public interface LikesService {
     boolean checkIsLiked(Long paperId, Long memberId);
}
