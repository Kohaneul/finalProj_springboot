package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Order;
import com.deli.project.domain.entity.OrderStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.deli.project.domain.entity.QOrder.order;

@Repository
public class OrderRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public OrderRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Order Order){
        em.persist(Order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }
    
    public List<Order> findAll(OrderSearch orderSearch){
        OrderStatus orderStatus = orderSearch.getOrderStatus();
        String loginId = orderSearch.getLoginId();
        return query.select(order).from(order).where(memberIdContains(loginId),equalOrderStatus(orderStatus)).fetch();
    }

    private BooleanExpression equalOrderStatus(OrderStatus orderStatus) {
        return order.orderStatus.eq(orderStatus);
    }

    private BooleanExpression memberIdContains(String loginId) {
        return order.loginId.contains(loginId);
    }


}
