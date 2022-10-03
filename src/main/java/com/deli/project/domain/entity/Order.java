package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Delivery delivery;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_id")
    private PickUp pickUp;
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

    public static Order orderStart(PickUp pickUp,Restaurant restaurant,OrderStatus orderStatus,Delivery delivery){
        Order order = new Order();
        order.setOrderStatus(orderStatus);

        return order;
    }
}
