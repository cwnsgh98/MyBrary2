package com.mybrary.backend.domain.contents.paper.service.impl;

import com.mybrary.backend.domain.contents.paper.entity.Paper;
import com.mybrary.backend.domain.contents.paper.repository.PaperRepository;
import com.mybrary.backend.domain.contents.paper.service.PaperService;
import com.mybrary.backend.domain.contents.thread.dto.ThreadPostDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaperServiceImpl implements PaperService {

    PaperRepository paperRepository;
    @Override
    public void createPaper(Paper paper) {
        paperRepository.save(paper);

    }
}
