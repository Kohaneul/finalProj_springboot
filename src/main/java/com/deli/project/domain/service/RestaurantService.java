package com.deli.project.domain.service;

import com.deli.project.domain.entity.Restaurant;
import com.deli.project.domain.repository.RestaurantDTO;
import com.deli.project.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Restaurant 저장소를 주입 받아 저장, 조회 기능을 실행하기 위한 서비스 함수.
 *
 * */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository repository;

    @Transactional
    public Long saveRestaurant(Restaurant restaurant){
        repository.save(restaurant);
        return restaurant.getId();
    }

    public Restaurant findOne(Long id){
        return repository.findOne(id);
    }

    public List<Restaurant> findAll(){
        return repository.findAll();
    }

    public List<Restaurant> findDto(RestaurantDTO restaurantDto){
        return repository.findAll(restaurantDto);
    }





}
