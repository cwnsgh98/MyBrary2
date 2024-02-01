package com.mybrary.backend.domain.mybrary.repository;

import com.mybrary.backend.domain.member.entity.Member;
import com.mybrary.backend.domain.mybrary.repository.custom.QuerydslMybraryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MybraryRepository extends JpaRepository<Member, Long>, QuerydslMybraryRepository {

}
