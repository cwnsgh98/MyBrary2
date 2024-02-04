package com.mybrary.backend.domain.contents.threads.service;

import com.mybrary.backend.domain.contents.paper.dto.PaperPostDto;
import com.mybrary.backend.domain.contents.threads.dto.DeleteThreadDto;
import com.mybrary.backend.domain.contents.threads.dto.GetThreadDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadGetDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadInfoGetDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadPostDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadSimpleGetDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadUpdateDto;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ThreadService {

    Long createThread(List<MultipartFile> fileList, ThreadPostDto threadPostDto) throws IOException;

    Long updateThread(ThreadUpdateDto threadUpdateDto);

    DeleteThreadDto deleteThread(Long threadId);

    List<GetThreadDto> getMainAllThread(Long memberId, Pageable pageable);

    List<ThreadInfoGetDto> getMyAllThread(Long memberId, Pageable pageable);

    List<ThreadInfoGetDto> getOtherAllThread(Long memberId, Pageable pageable);

}
