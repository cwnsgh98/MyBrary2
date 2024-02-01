package com.mybrary.backend.domain.contents.thread.dto;

import com.mybrary.backend.domain.contents.paper.dto.PaperPostDto;
import com.mybrary.backend.domain.contents.paper.entity.Paper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThreadPostDto {

    /**
     *  스레드 저장 요청
     *  페이퍼 리스트와
     *  공개여부, 스크랩 허용여부 받기 -> 페이퍼 저장 때 페이퍼마다 적용해야함
     */

    private Long bookId;
    private Long mybraryId;

    private List<PaperPostDto> paperPostDtoList;
    private List<String> tagList;
    private List<String> mentionList;

    /* 쓰레드의 공개/스크랩 여부는 페이퍼들에 동일하게 적용 */
    private boolean isPaperPublic;
    private boolean isScarpEnable;

}
