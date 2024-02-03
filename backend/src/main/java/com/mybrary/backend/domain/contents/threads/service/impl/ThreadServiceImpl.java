package com.mybrary.backend.domain.contents.threads.service.impl;


import com.mybrary.backend.domain.book.repository.BookRepository;
import com.mybrary.backend.domain.contents.paper.dto.GetFollowingPaperDto;
import com.mybrary.backend.domain.contents.paper.dto.PaperPostDto;
import com.mybrary.backend.domain.contents.paper.entity.Paper;
import com.mybrary.backend.domain.contents.paper.repository.PaperRepository;
import com.mybrary.backend.domain.contents.paperImage.entity.PaperImage;
import com.mybrary.backend.domain.contents.paperImage.repository.PaperImageRepository;
import com.mybrary.backend.domain.contents.scrap.entity.Scrap;
import com.mybrary.backend.domain.contents.scrap.repository.ScrapRepository;
import com.mybrary.backend.domain.contents.tag.entity.Tag;
import com.mybrary.backend.domain.contents.tag.repository.TagRepository;
import com.mybrary.backend.domain.contents.threads.dto.GetFollowingThreadDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadGetDto;
import com.mybrary.backend.domain.contents.threads.dto.ThreadPostDto;
import com.mybrary.backend.domain.contents.threads.entity.Threads;
import com.mybrary.backend.domain.contents.threads.repository.ThreadRepository;
import com.mybrary.backend.domain.contents.threads.service.ThreadService;
import com.mybrary.backend.domain.image.entity.Image;
import com.mybrary.backend.domain.image.repository.ImageRepository;
import com.mybrary.backend.domain.image.service.ImageService;
import com.mybrary.backend.domain.member.repository.MemberRepository;
import com.mybrary.backend.domain.mybrary.repository.MybraryRepository;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final PaperRepository paperRepository;
    private final PaperImageRepository paperImageRepository;
    private final MybraryRepository mybraryRepository;
    private final TagRepository tagRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ScrapRepository scrapRepository;


    @Transactional
    @Override
    public Long createThread(List<MultipartFile> fileList, ThreadPostDto threadPostDto)
        throws IOException {

        Long mybraryId = threadPostDto.getMybraryId();
        Threads thread = Threads.builder()
            .mybrary(mybraryRepository.findByMybraryId(mybraryId))
            .build();
        threadRepository.save(thread);

        /* 스레드 관련정보 */
        Long threadId = thread.getId();
        Long bookId = threadPostDto.getBookId();
        Long memberId = threadPostDto.getMemberId();
        boolean isPaperPublic = threadPostDto.isPaperPublic();
        boolean isScrapEnabled = threadPostDto.isScrapEnable();

        // 이미지 업로드 -> 이미지 생성 -> 페이퍼이미지 생성 -> 페이퍼 생성
        /* paper 객체 하나씩 생성하고 저장 */
        List<PaperPostDto> paperPostDtoList = threadPostDto.getPaperPostDtoList();
        int cnt = 0;
        int paperSeq = 1;
        for (PaperPostDto dto : paperPostDtoList) {
            /* paper 객체 생성 */
            Paper paper = dto.getPaper();
            paper.setMember(memberRepository.findMemberById(memberId));
            paper.setThreads(thread);
//            paper.setLayoutType(dto.getPaper().getLayoutType());
//            paper.setContent1(dto.getPaper().getContent1());
//            paper.setContent2(dto.getPaper().getContent2());
            paper.setPaperPublic(isPaperPublic);
            paper.setScrapEnabled(isScrapEnabled);
            paperRepository.save(paper);

            /* scrap 객체 생성 */
            Scrap scrap = Scrap.builder()
                .paper(paper)
                .book(bookRepository.findBookById(bookId))
                .paperSeq(paperSeq++)
                .build();
            scrapRepository.save(scrap);

            /* tag 목록 생성 */
            List<Tag> tagList = dto.getTagList();
            for(Tag tag : tagList){
                tag.setPaper(paper);
            }
            tagRepository.saveAll(tagList);

            /* image 객체 두장 생성, paperImage 객체도 생성 */
            /* 이거 그냥 for문 빼자 두개밖에없으니까 */
            int imageSeq = 1;
            for (int i = cnt; i < 2; i++) {
                if (!fileList.get(i).isEmpty() && fileList.get(i) != null) {
                    Image image = imageService.uploadImage(fileList.get(i));
                    imageRepository.save(image);
                    PaperImage paperImage = new PaperImage();
                    paperImage.setPaper(paper);
                    paperImage.setImage(image);
                    paperImage.setImageSeq(imageSeq++);
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

//    @Transactional
//    @Override
//    public Long deleteThread(Long threadId) {
//        return paperRepository.deleteAllByThreadId(threadId);
//    }

    @Transactional
    @Override
    public List<GetFollowingThreadDto> getMainAllThread(Long memberId, Pageable pageable) {

        /* following중인 멤버(본인 포함) 의 쓰레드 최대 5개와 관련된 정보 dto 생성(정렬된상태) */
        List<GetFollowingThreadDto> threadDtoList = threadRepository.getFollowingThreadDtoResults(
            memberId, pageable);
        int getCount = threadDtoList.size();
        /* 나와 팔로잉중인 member들을 제외한 나머지 member의 쓰레드 10 - n개 관련정보 dto list 생성 */
//        threadDtoList.addAll(threadRepository.getRandomThreadDtoResults(memberId, 10 - getCount));

        /* followingThreadDtos의 각 threadId에 해당하는 paper관련 정보 조회 */
        for (GetFollowingThreadDto threadsDto : threadDtoList) {
            /* threadId에 해당하는 paper 관련 정보 dto 목록 조회 */
            List<GetFollowingPaperDto> getFollowingPaperDtoList = paperRepository.getFollowingPaperDtoResults(
                threadsDto.getThreadId());
            /* 페이퍼 관련정보 처리 로직 */
            for (GetFollowingPaperDto paperDto : getFollowingPaperDtoList) {
                /* 좋아요 여부 판단 */
                boolean isLiked = checkIsLiked(paperDto.getId(), memberId);
                paperDto.setLiked(isLiked);
                /* 태그목록 포함 처리 */
                paperDto.setTagList(getTagNameList(paperDto.getId()));
                /* 페이퍼에 해당되는 이미지 url들 포함 처리 */
                List<String> imageUrls = getPaperImages(paperDto.getId());
                paperDto.setImageUrl1(imageUrls.get(0));
                paperDto.setImageUrl1(imageUrls.get(1));

            }

            /* paper 관련정보 dto list -> thread 관련정보 dto 매핑*/
//            threadsDto.setPaperList(getFollowingPaperDtoList);
        }

        return threadDtoList;
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


    /* 밑에 메소드들 다 옮길예정 */
    public boolean checkIsLiked(Long paperId, Long memberId) {
        return paperRepository.isLikedPaper(paperId, memberId)
            .map(like -> !like.isDeleted())
            .orElse(false);
    }

    public List<String> getTagNameList(Long paperId) {
        Optional<List<Tag>> optionalTagList = tagRepository.getTagsByPaperId(paperId);
        List<Tag> tagList;
        // tag 값이 있으면 반환, null이라면 빈 객체 반환
        tagList = optionalTagList.orElseGet(ArrayList::new);

        // Tag의 tagName 필드를 String 리스트로 변환
        List<String> tagNames = tagList.stream()  // Stream<Tag> 생성
            .map(Tag::getTagName)    // Stream<String>으로 변환
            .toList();     // 결과를 List<String>로 변환

        return tagNames;
    }

    public List<String> getPaperImages(Long paperId) {
        /* 페이퍼 - 페이퍼이미지 - 이미지 조인해서 이미지 url 얻어오기 */
        return imageRepository.findByPaperId(paperId);
    }

}

