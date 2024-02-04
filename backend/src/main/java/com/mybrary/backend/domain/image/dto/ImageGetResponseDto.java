package com.mybrary.backend.domain.image.dto;

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
public class ImageGetResponseDto {

    /**
     *  이미지 정보 조회
     */

    private Long imageId;
    private String imageName;
    private String url;
    private String imageUrl;
    private String format;
    private String size;

}
