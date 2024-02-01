package com.mybrary.backend.domain.contents.thread.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
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
public class GetFollowingThreadDto {

    /* 쓰레드 관련 정보 */
    private Long bookId;

    private Long threadId;

    private LocalDateTime threadsCreatedAt;

    /* 작성자 member 관련 정보 */
    private Long memberId;

    private String memberName;

    private String nickname;

    private String profileUrl;

    /* 해당 쓰레드에 포함된 paperId 목록 */
    private List<Long> paperIdList;


}
