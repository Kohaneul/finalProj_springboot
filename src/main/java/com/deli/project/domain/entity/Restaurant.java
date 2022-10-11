package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name="restaurant_name")
    private String restaurantName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pickup_id")
    private PickUp pickUp;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    private boolean isShow;

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setCategory(Category category) {
        this.category = category;
        //category.getRestaurants().add(this);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public void setPickUp(PickUp pickUp) {
        this.pickUp = pickUp;
    }

    public static Restaurant setRestaurant(String restaurantName, Address address, Category category) {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setRestaurantName(restaurantName);
        restaurant.setCategory(category);
        restaurant.setShow(false);
        return restaurant;
    }
}
