package com.deli.project.domain.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 식당 정보 테이블
 * */
@Entity
@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name="restaurant_name")
    private String restaurantName;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    public List<Menu> menuList = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    private double score;
    private int minOrderPrice;

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void setMinOrderPrice(int minOrderPrice) {
        this.minOrderPrice = minOrderPrice;
    }


    public void setScore(double score) {
        this.score = score;
    }
    public static Restaurant setRestaurant(String restaurantName, Address address, Category category, double score, int minOrderPrice) {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setRestaurantName(restaurantName);
        restaurant.setCategory(category);
        restaurant.setMinOrderPrice(minOrderPrice);
        restaurant.setScore(score);
        return restaurant;
    }
}
