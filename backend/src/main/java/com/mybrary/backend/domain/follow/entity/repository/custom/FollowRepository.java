package com.mybrary.backend.domain.follow.entity.repository.custom;

import com.mybrary.backend.domain.contents.thread.entity.Threads;
import com.mybrary.backend.domain.contents.thread.repository.custom.QuerydslThreadRepositoryCustom;
import com.mybrary.backend.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>,
    QuerydslFollowRepositoryCustom {

}
