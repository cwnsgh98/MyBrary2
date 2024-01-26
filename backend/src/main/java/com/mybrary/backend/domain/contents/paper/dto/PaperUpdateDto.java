package com.mybrary.backend.domain.contents.paper.dto;

import com.mybrary.backend.domain.image.dto.ImagePostDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperUpdateDto {

    /**
     *  스레드 수정 요청
     *  스레드 저장과 마찬가지로 페이퍼리스트 포함
     */

    private Long paperId;
    private int layoutType;
    private String content1;
    private String content2;
    private List<String> tagList;
    private List<Long> mentionIdList;

}