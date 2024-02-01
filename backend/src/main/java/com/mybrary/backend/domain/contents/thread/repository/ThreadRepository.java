package com.mybrary.backend.domain.contents.thread.repository;

import com.mybrary.backend.domain.contents.thread.entity.Threads;
import com.mybrary.backend.domain.contents.thread.repository.custom.QuerydslThreadRepositoryCustom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Threads, Long>,
    QuerydslThreadRepositoryCustom {

}
