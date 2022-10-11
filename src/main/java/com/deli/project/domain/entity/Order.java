package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;
    private String loginId;
    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Delivery delivery;

    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY)
    private Board board;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    // WAITING,START,COMPLETE

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public static Order createOrder(Restaurant restaurant,String loginId){
        Order order = new Order();
        order.setLoginId(loginId);
        order.setRestaurant(restaurant);
        order.setOrderStatus(OrderStatus.START);
        order.setDelivery(new Delivery());
        return order;
    }
}
