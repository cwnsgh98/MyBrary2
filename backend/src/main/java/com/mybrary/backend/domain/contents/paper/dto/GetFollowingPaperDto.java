package com.mybrary.backend.domain.contents.paper.dto;

import com.mybrary.backend.domain.contents.paper.entity.Paper;
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
public class GetFollowingPaperDto extends Paper {

    private String imageUrl1;

    private String imageUrl2;

    private boolean isLiked;

    private List<String> tagList;
}
