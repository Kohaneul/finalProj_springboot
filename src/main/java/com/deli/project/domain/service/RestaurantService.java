package com.deli.project.domain.service;

import com.deli.project.domain.entity.Restaurant;
import com.deli.project.domain.repository.RestaurantDto;
import com.deli.project.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository repository;

    @Transactional
    public Long saveRestaurant(Restaurant Restaurant){
        repository.save(Restaurant);
        return Restaurant.getId();
    }

    public Restaurant findOne(Long id){
        return repository.findOne(id);
    }

    public List<Restaurant> findAll(boolean isShow){
        return repository.findAll(isShow);
    }

    @Transactional
    public List<Restaurant> findDto(RestaurantDto restaurantDto){
        return repository.findAll(restaurantDto);
    }



}
