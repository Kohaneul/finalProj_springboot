package com.deli.project.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * 식당 조회를 하기 위한 DTO
 *    특정 조건에 부합되는 식당 조회
 *      - 로그인 아이디가 특정 문자열을 포함하는가
 *      - 식당 주소가 특정 주소를 포함하는가
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private Long categoryId;
    private String address;
}
