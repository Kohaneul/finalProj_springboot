package com.deli.project.domain.service;

import com.deli.project.domain.entity.Order;
import com.deli.project.domain.repository.OrderRepository;
import com.deli.project.domain.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository repository;
    @Transactional
    public Long saveOrder(Order Order){
        repository.save(Order);
        return Order.getId();
    }

    public Order findOne(Long id){
        return repository.findOne(id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
        return repository.findAll(orderSearch);
    }



}
