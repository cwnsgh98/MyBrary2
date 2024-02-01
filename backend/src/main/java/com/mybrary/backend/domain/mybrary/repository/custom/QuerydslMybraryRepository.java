package com.mybrary.backend.domain.mybrary.repository.custom;

import com.mybrary.backend.domain.mybrary.entity.Mybrary;

public interface QuerydslMybraryRepository {
    Mybrary findByMybraryId(Long mybraryId);
}
