package com.mybrary.backend.domain.contents.likes.service;

import com.mybrary.backend.domain.contents.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService{
    private final LikesRepository likesRepository;

    /* 사용자가 해당 paper에 좋아요를 눌렀는지 여부 판단 */
    @Override
    public boolean checkIsLiked(Long paperId, Long memberId) {
        return likesRepository.isLikedPaper(paperId, memberId)
            .map(likes -> !likes.isDeleted())
            .orElse(false);
    }
}
