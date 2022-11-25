package com.deli.project.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * 게시글 조회를 하기 위한 DTO
 *   작성자 ID,게시글 제목 : 특정 문자열을 포함하는가
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardSearchDto {
    private String nickName;
    private String title;
}
