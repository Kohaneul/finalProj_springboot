package com.deli.project.domain.entity;

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

    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY)
    private Board board;

    private String restaurantName;
    @OneToMany(mappedBy = "orderCheck")
    private List<Menu> menuList = new ArrayList<>();


    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void addMenu(Menu...menus) {
        Arrays.stream(menus).forEach(m->menuList.add(m));
    }


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    // WAITING,START,COMPLETE


    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public static OrderCheck createOrder(String restaurantName , String loginId,List<Menu> menus){
        OrderCheck order = new OrderCheck();
        order.setLoginId(loginId);
        order.setRestaurantName(restaurantName);
        order.setOrderStatus(OrderStatus.START);
        order.setMenuList(menus);
        return order;
    }
}