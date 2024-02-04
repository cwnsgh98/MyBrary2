package com.mybrary.backend.domain.contents.paper.dto;

import com.mybrary.backend.domain.member.dto.MemberGetDto;
import com.mybrary.backend.domain.member.dto.MemberInfoDto;
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
public class PaperInBookGetDto {

    /**
     *  책에 포함될 페이퍼에 대한 List
     *  다른점 : 이건 페이퍼를 작성한 사람 정보까지 있음
     */

    private Long paperId;
    private String createdAt;
    private MemberInfoDto writer;
    private int layoutType;
    private String content1;
    private String content2;
    private String imageUrl1;
    private String imageUrl2;
    private List<String> tagList;
    private List<MemberInfoDto> mentionList;
    private int likesCount;
    private int commentCount;
    private int scrapCount;
    private boolean isLiked;
    private boolean isOwner;

}
