package com.mybrary.backend.domain.contents.paper.service;

import com.mybrary.backend.domain.contents.paper.dto.PaperScrapDto;
import com.mybrary.backend.domain.contents.paper.dto.PaperShareDto;
import com.mybrary.backend.domain.contents.paper.entity.Paper;

public interface PaperService {

    Long scrapPaper(PaperScrapDto scrap);

    Long sharePaper(PaperShareDto share);
}
