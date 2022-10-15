package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
/**
 * 메뉴(식당) 테이블
 * */


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Menu {
    @GeneratedValue
    @Id @Column(name="menu_id")
    private Long id;
    private String name;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Menu(String name, int price,Restaurant restaurant){
        this.name= name;
        this.price= price;
        this.restaurant = restaurant;
    }


}
