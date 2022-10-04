package com.deli.project.domain.repository;

import com.deli.project.domain.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderSearch {
    private String loginId;
    private String restaurantName;
    private OrderStatus orderStatus;
}
