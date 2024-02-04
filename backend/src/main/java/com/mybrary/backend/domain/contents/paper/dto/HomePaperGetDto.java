package com.mybrary.backend.domain.contents.paper.dto;

import com.mybrary.backend.domain.member.dto.MemberGetDto;
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
public class HomePaperGetDto {

    /**
     *  홈에서 스레드 조회할 때 페이퍼 List에 포함
     *
     */


    private Long paperId;
    private String createAt;
    private int layoutType;
    private String content1;
    private String content2;
    private String imageUrl1;
    private String imageUrl2;
    private List<String> tagList;
    private List<MemberGetDto> mentionList;
    private int likeCount;
    private int commentCount;
    private int scrapCount;
    private boolean isLiked;
    private boolean isPaperPublic;
    private boolean isScrapEnable;

}
