package com.deli.project.domain.repository;

import com.deli.project.domain.entity.OrderCheck;
import com.deli.project.domain.entity.OrderStatus;
import com.deli.project.domain.entity.QOrderCheck;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.deli.project.domain.entity.QOrderCheck.orderCheck;

@Repository
public class OrderCheckRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public OrderCheckRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(OrderCheck Order){
        em.persist(Order);
    }

    public OrderCheck findOne(Long id){
        return em.find(OrderCheck.class, id);
    }
    
    public List<OrderCheck> findAll(OrderSearch orderSearch){
        OrderStatus orderStatus = orderSearch.getOrderStatus();
        String loginId = orderSearch.getLoginId();
        return query.select(orderCheck).from(orderCheck).where(memberIdContains(loginId),equalOrderStatus(orderStatus)).fetch();
    }

    private BooleanExpression equalOrderStatus(OrderStatus orderStatus) {
        return orderCheck.orderStatus.eq(orderStatus);
    }

    private BooleanExpression memberIdContains(String loginId) {
        return orderCheck.loginId.contains(loginId);
    }


}
