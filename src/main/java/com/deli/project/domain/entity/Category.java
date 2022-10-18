package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 테이블
 * */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(name="category_name")
    private String categoryName;

//    @OneToMany(mappedBy = "category")
//    private List<Restaurant> restaurants = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pickup_id")
    private PickUp pickUp;

    public void setPickUp(PickUp pickUp) {
        this.pickUp = pickUp;

        for (Category category : pickUp.getCategory()) {
            category.setPickUp(pickUp);
        }
    }

    public Category(String categoryName){
        this.categoryName = categoryName;
    }


    public Category(String categoryName, PickUp pickUp) {
        this.categoryName = categoryName;
        this.pickUp = pickUp;
    }
}
