package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Restaurant;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
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




}
