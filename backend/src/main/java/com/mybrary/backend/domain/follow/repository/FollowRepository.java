package com.mybrary.backend.domain.follow.repository;

import com.mybrary.backend.domain.follow.entity.Follow;
import com.mybrary.backend.domain.follow.repository.custom.QuerydslFollowRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>,
    QuerydslFollowRepositoryCustom {

}
