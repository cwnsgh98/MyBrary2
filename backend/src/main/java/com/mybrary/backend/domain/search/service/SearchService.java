package com.mybrary.backend.domain.search.service;

import com.mybrary.backend.domain.contents.threads.dto.ThreadSimpleGetDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    List<ThreadSimpleGetDto> searchThread(String keyword, Pageable page);

}
