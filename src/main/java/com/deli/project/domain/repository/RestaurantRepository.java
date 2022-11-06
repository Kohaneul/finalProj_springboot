package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Restaurant;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.deli.project.domain.entity.QRestaurant.restaurant;

/**
 * 식당 정보 저장소
 */
@Repository
@Slf4j
public class RestaurantRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public RestaurantRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Restaurant restaurant){
        em.persist(restaurant);
    }

    public Restaurant findOne(Long id){
        return em.find(Restaurant.class, id);
    }
    
    public List<Restaurant> findAll(){
        return em.createQuery("select c from Restaurant c order by c.score desc",Restaurant.class).getResultList();
    }

    public List<Restaurant> findAll(RestaurantDTO restaurantDto){
        String address = restaurantDto.getAddress();
        Long categoryId = restaurantDto.getCategoryId();
        return query.select(restaurant).from(restaurant).where(containsCity(address),equalsCategoryId(categoryId)).orderBy(restaurant.score.desc()).fetch();
    }

    private BooleanExpression equalsCategoryId(Long categoryId) {
        return restaurant.category.id.eq(categoryId);
    }

    private BooleanExpression containsCity(String address) {
        address = address.split(" ")[1];
        return restaurant.address.state.contains(address);
    }



}
