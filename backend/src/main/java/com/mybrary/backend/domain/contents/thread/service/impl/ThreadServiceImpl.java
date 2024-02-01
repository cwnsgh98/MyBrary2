package com.mybrary.backend.domain.contents.thread.service.impl;

import com.mybrary.backend.domain.contents.paper.dto.PaperPostDto;
import com.mybrary.backend.domain.contents.paper.entity.Paper;
import com.mybrary.backend.domain.contents.paper.repository.PaperRepository;
import com.mybrary.backend.domain.contents.paperImage.entity.PaperImage;
import com.mybrary.backend.domain.contents.paperImage.repository.PaperImageRepository;
import com.mybrary.backend.domain.contents.thread.dto.ThreadGetDto;
import com.mybrary.backend.domain.contents.thread.dto.ThreadPostDto;
import com.mybrary.backend.domain.contents.thread.entity.Threads;
import com.mybrary.backend.domain.contents.thread.repository.ThreadRepository;
import com.mybrary.backend.domain.contents.thread.service.ThreadService;
import com.mybrary.backend.domain.follow.entity.Follow;
import com.mybrary.backend.domain.image.entity.Image;
import com.mybrary.backend.domain.image.repository.ImageRepository;
import com.mybrary.backend.domain.image.service.ImageService;
import com.mybrary.backend.domain.member.entity.Member;
import com.mybrary.backend.domain.member.repository.MemberRepository;
import com.mybrary.backend.domain.member.service.MemberService;
import com.mybrary.backend.domain.mybrary.repository.custom.QuerydslMybraryRepository;
import com.mybrary.backend.global.exception.member.InvalidLoginAttemptException;
import com.mybrary.backend.global.format.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;
    private final QuerydslMybraryRepository querydslMybraryRepository;
    private final ImageRepository imageRepository;
    private final PaperRepository paperRepository;
    private final PaperImageRepository paperImageRepository;
    private final ImageService imageService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @Transactional
    @Override
    public Long createThread(List<MultipartFile> fileList, ThreadPostDto threadPostDto)
        throws IOException {

        /* 스레드 생성 */
        Long mybraryId = threadPostDto.getMybraryId();
        Threads thread = Threads.builder()
                                .mybrary(querydslMybraryRepository.findByMybraryId(mybraryId))
                                .build();
        threadRepository.save(thread);
        Long threadId = thread.getId();

        // 이미지 업로드 -> 이미지 생성 -> 페이퍼이미지 생성 -> 페이퍼 생성
        /* paper 객체 하나씩 생성하고 저장 */
        List<PaperPostDto> paperPostDtoList = threadPostDto.getPaperPostDtoList();
        int cnt = 0;
        for (PaperPostDto dto : paperPostDtoList) {
            Paper paper = dto.getPaper();
            paper.setThread(thread);
            paperRepository.save(paper);

            /* 이미지 두개씩 생성, 각 이미지에 해당하는 페이퍼이미지도 생성 */
            for (int i = cnt; i < 2; i++) {
                if (!fileList.get(i).isEmpty() && fileList.get(i) != null) {
                    Image image = imageService.uploadImage(fileList.get(i));
                    imageRepository.save(image);
                    PaperImage paperImage = new PaperImage();
                    paperImage.setPaper(paper);
                    paperImage.setImage(image);
                    paperImageRepository.save(paperImage);
                }
            }
            cnt = cnt + 2;
        }

        return threadId;
    }

    @Transactional
    @Override
    public Long updateThread(ThreadPostDto threadPostDto) {
        return null;
    }

    @Transactional
    @Override
    public Long deleteThread(Long threadId) {
        return paperRepository.deleteAllByThreadId(threadId);
    }

    @Transactional
    @Override
    public List<ThreadGetDto> getMainAllThread(Long memberId) {
        /*  팔로워 목록 조회 */
        Optional<Member> member = memberRepository.findById(memberId);
        List<Follow> followers = member.get().getFollowingList();

        /*  */


        return null;
    }

    @Transactional
    @Override
    public List<ThreadGetDto> getMyAllThread() {
        return null;
    }

    @Transactional
    @Override
    public List<ThreadGetDto> getOtherAllThread() {
        return null;
    }
}
