package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    // WAITING,START,COMPLETE


    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }



    public static OrderCheck createOrder(Restaurant restaurant, String loginId,Menu ...menus){
        OrderCheck order = new OrderCheck();
        order.setLoginId(loginId);
        order.setRestaurant(restaurant);
        order.setOrderStatus(OrderStatus.START);
        for (Menu menu : menus) {
            order.totalPrice +=menu.getPrice();
        }
        return order;
    }
}
