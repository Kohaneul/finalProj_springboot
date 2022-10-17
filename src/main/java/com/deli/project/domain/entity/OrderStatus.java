package com.deli.project.domain.entity;
/**
 * 현재 주문 진행상황
 * WAITING : 주문대기 ( 게시글만 작성한 상황)
 * START : 회원 모집 시작
 * COMPLETE : 회원 모집 완료
 * */
public enum OrderStatus {
    WAITING,START,COMPLETE
}
