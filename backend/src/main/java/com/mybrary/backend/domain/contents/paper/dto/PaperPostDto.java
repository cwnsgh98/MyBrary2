package com.mybrary.backend.domain.contents.paper.dto;

import com.mybrary.backend.domain.contents.paper.entity.Paper;
import com.mybrary.backend.domain.image.dto.ImagePostDto;
import com.mybrary.backend.domain.image.entity.Image;
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
public class PaperPostDto {

    /**
     *  스레드 저장 요청
     *  스레드 저장할 때 스레드 객체 안에 List로 같이 받을 것임
     */

    private Paper paper;
    private List<String> tagList;
    private List<Long> mentionIdList;

}
