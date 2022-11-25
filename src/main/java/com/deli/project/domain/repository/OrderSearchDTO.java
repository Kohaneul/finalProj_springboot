package com.deli.project.domain.repository;

import com.deli.project.domain.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * 전체 주문 내역 조회를 하기 위한 DTO
 *    특정 조건에 부합되는 주문건 조회
 *      - 주문자 아이디가 특정 문자열을 포함하는가
 *      - 식당이름이 특정 문자열을 포함하는가
 *      - 주문상태 (주문대기, 시작, 취소)
 */
@Getter
@Setter
@AllArgsConstructor
public class OrderSearchDTO {
    private String nickName;
    private String restaurantName;
    private OrderStatus orderStatus;
}
