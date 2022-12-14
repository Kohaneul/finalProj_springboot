package com.deli.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 주문정보 확인 테이블
 * */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_check")
public class OrderCheck {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    private String loginId;
    private String nickName;

    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY)
    private Board board;

    private String restaurantName;
    @OneToMany(mappedBy = "orderCheck")
    private List<Menu> menuList = new ArrayList<>();


    public void setMenuList(Menu menu) {
        menu.setOrderCheck(this);
        this.menuList.add(menu);
    }


    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    // WAITING,START,COMPLETE


    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = OrderStatus.START;
    }


    public void setBoard(Board board) {
        this.board = board;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public static OrderCheck createOrder(String restaurantName,String loginId, String nickName,List<Menu> menus){
        OrderCheck order = new OrderCheck();
        order.setNickName(nickName);
        order.setLoginId(loginId);
        order.setOrderStatus(OrderStatus.WAITING);
        order.setRestaurantName(restaurantName);
        menus.stream().forEach(i->order.setMenuList(i));
        return order;
    }
}