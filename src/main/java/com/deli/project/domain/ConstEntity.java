package com.deli.project.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 세션값으로 저장할 session 변수를 상수로 정의
 * SESSION : 로그인시 생성 (형식 : UUID + / +ID)
 * USER_SESSION : SESSION 값에서 ID 값만 추출하여 ADMIN, 일반 계정 구분
 * PICKUP_SESSION : PICKUP 장소 선택시 PK 값 세션에 저장
 * CATEGORY_SESSION : 카테고리 선택시 PK 값 세션에 저장
 * RESTAURANT_SESSION : 식당 선택시 PK 값 세션에 저장
 * MENU_SESSION : 식당 중 메뉴 선택시 PK 값 세션에 저장
 * ORDER_CHECK_SESSION : 글쓰기 전 확인 후 PK 값 세션에 저장
 *
 * */
public abstract class ConstEntity {
    public static Map<String,Long> ConstMap = new HashMap<>();
    public final static String SESSION = "SESSION_ID";
    public final static String USER_SESSION = "USER_ID";
    public final static String PICKUP_SESSION = "PICKUP_ID";
    public final static String CATEGORY_SESSION = "CATEGORY_ID";
    public final static String RESTAURANT_SESSION = "RESTAURANT_ID";
    public final static String MENU_SESSION = "MENU_ID";

    public final static String LOGIN_ID = "LOGIN_ID";
    public final static String ORDER_CHECK_SESSION = "ORDER_CHECK_ID";





}
