package com.mybrary.backend.domain.contents.threads.service;

import com.mybrary.backend.domain.contents.paper.dto.GetFollowingPaperDto;
import com.mybrary.backend.domain.contents.threads.dto.GetFollowingThreadDto;
import com.mybrary.backend.domain.contents.threads.dto.GetThreadDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadGetDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadPostDto;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ThreadService {

    Long createThread(List<MultipartFile> files, ThreadPostDto threadPostDto) throws IOException;

    Long updateThread(ThreadPostDto threadPostDto);

//    Long deleteThread(Long threadId);

    List<GetFollowingThreadDto> getMainAllThread(Long memberId, Pageable pageable);

    List<ThreadGetDto> getMyAllThread();

    List<ThreadGetDto> getOtherAllThread();

}
