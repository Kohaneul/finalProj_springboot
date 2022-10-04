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
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Restaurant>  restaurants = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pickup_id")
    private PickUp pickUp;

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        for (Restaurant restaurant : restaurants) {
            restaurant.setCategory(this);
        }
    }

    public Category(String categoryName, PickUp pickUp) {
        this.categoryName = categoryName;
        this.pickUp = pickUp;
    }
}
