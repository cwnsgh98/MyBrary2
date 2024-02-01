package com.mybrary.backend.domain.contents.thread.service;

import com.mybrary.backend.domain.contents.thread.dto.ThreadGetDto;
import com.mybrary.backend.domain.contents.thread.dto.ThreadPostDto;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ThreadService {

    Long createThread(List<MultipartFile> files, ThreadPostDto threadPostDto) throws IOException;

    Long updateThread(ThreadPostDto threadPostDto);

    Long deleteThread(Long threadId);

    List<ThreadGetDto> getMainAllThread(Long memberId);

    List<ThreadGetDto> getMyAllThread();

    List<ThreadGetDto> getOtherAllThread();

}
