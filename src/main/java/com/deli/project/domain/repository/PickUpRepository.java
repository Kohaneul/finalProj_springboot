package com.deli.project.domain.repository;

import com.deli.project.domain.entity.PickUp;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PickUpRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public PickUpRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(PickUp pickUp){
        em.persist(pickUp);
    }

    public PickUp findOne(Long id){
        return em.find(PickUp.class, id);
    }
    public List<PickUp> findAll(){
        return em.createQuery("select p from PickUp p",PickUp.class).getResultList();
    }




}
