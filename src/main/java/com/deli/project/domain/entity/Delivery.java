package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * 게시글 테이블
 * */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private OrderCheck order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;  //    PICKUP,ING,COMPLETE

    public void setOrder(OrderCheck order) {
        this.order = order;

    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    public Delivery(DeliveryStatus deliveryStatus){
        this.deliveryStatus = deliveryStatus;

    }
}
