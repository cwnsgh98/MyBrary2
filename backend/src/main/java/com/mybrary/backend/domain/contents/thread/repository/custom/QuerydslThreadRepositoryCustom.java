package com.mybrary.backend.domain.contents.thread.repository.custom;

import com.mybrary.backend.domain.contents.thread.entity.Threads;
import com.mybrary.backend.domain.member.entity.Member;
import java.util.List;
import java.util.Optional;

public interface QuerydslThreadRepositoryCustom {

    Optional<Threads> findByThreadId(Long threadId);

}
