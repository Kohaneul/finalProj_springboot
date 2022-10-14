package com.deli.project.domain.service;

import com.deli.project.domain.entity.OrderCheck;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderCheckService {
    private final OrderCheckRepository repository;
    @Transactional
    public Long saveOrder(OrderCheck orderChecker){
        repository.save(orderChecker);
        return orderChecker.getId();
    }

    public OrderCheck findOne(Long id){
        return repository.findOne(id);
    }

    public List<OrderCheck> findAll(OrderSearch orderSearch){
        return repository.findAll(orderSearch);
    }



}
