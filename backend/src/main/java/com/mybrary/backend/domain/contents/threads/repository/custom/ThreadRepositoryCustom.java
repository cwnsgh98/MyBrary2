package com.mybrary.backend.domain.contents.threads.repository.custom;

import com.mybrary.backend.domain.contents.threads.dto.GetFollowingThreadDto;
import com.mybrary.backend.domain.contents.threads.entity.Threads;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface ThreadRepositoryCustom {

    Optional<Threads> findByThreadId(Long threadId);

     List<GetFollowingThreadDto> getFollowingThreadDtoResults(Long memberId, Pageable pageable);

//    List<GetFollowingThreadDto> getRandomThreadDtoResults(Long memberId, int count);
}
