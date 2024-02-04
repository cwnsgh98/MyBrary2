package com.mybrary.backend.domain.contents.threads.dto;

import com.mybrary.backend.domain.contents.paper.dto.PaperPostDto;
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

    private Long memberId;

    private List<PaperPostDto> paperPostDto;

}
