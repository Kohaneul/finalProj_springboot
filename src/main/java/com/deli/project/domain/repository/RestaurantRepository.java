package com.deli.project.domain.repository;

import com.deli.project.domain.entity.QRestaurant;
import com.deli.project.domain.entity.Restaurant;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
public class RestaurantRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public RestaurantRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Restaurant Restaurant){
        em.persist(Restaurant);
    }

    public Restaurant findOne(Long id){
        return em.find(Restaurant.class, id);
    }
    
    public List<Restaurant> findAll(boolean isShow){
        return em.createQuery("select c from Restaurant c where c.isShow = :isShow",Restaurant.class).setParameter("isShow",isShow).getResultList();
    }

    public List<Restaurant> findAll(RestaurantDto restaurantDto){
        String address = restaurantDto.getAddress();
        Long categoryId = restaurantDto.getCategoryId();
        return query.select(QRestaurant.restaurant).from(QRestaurant.restaurant).where(containsCity(address),equalsCategoryId(categoryId)).fetch();
    }

    private BooleanExpression equalsCategoryId(Long categoryId) {
        log.info("cccccccc={}",categoryId);
        return QRestaurant.restaurant.category.id.eq(categoryId);
    }

    private BooleanExpression containsCity(String address) {
        address = address.split(" ")[1];
        log.info("address={}",address);

        return QRestaurant.restaurant.address.state.contains(address);
    }



}
