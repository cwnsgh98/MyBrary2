package com.mybrary.backend.domain.contents.paperImage.repository;

import com.mybrary.backend.domain.contents.paperImage.entity.PaperImage;
import com.mybrary.backend.domain.contents.thread.entity.Threads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperImageRepository extends JpaRepository<PaperImage, Long> {

}
