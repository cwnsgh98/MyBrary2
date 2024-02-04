package com.mybrary.backend.domain.contents.likes.repository;

import com.mybrary.backend.domain.contents.likes.entity.Likes;
import com.mybrary.backend.domain.contents.likes.repository.custom.LikesRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long>, LikesRepositoryCustom {

}
