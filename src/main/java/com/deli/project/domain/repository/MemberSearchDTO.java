package com.deli.project.domain.repository;

import com.deli.project.domain.entity.MemberSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * 회원 조회를 하기 위한 DTO
 *    특정 조건에 부합되는 회원 조회
 *      - 회원 등급 : BASIC, ADMIN, BLACK
 *      - 로그인 아이디가 특정 문자열을 포함하는가
 */

@Getter
@Setter
@AllArgsConstructor
public class MemberSearchDTO {
    private String loginId;
    private MemberSort memberSort;
}
