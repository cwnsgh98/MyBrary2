package com.mybrary.backend.domain.contents.paper.service;

import com.mybrary.backend.domain.contents.paper.entity.Paper;
import org.springframework.stereotype.Service;

public interface PaperService {

    /* 쓰레드 생성 요청에 페이퍼 생성 요청까지 포함되어 있으므로 ThreadPostDto 사용함 */
    void createPaper(Paper paper);
}
