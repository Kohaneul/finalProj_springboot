package com.deli.project.domain.repository;

import com.deli.project.domain.entity.OrderCheck;
import com.deli.project.domain.entity.OrderStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.deli.project.domain.entity.QOrderCheck.orderCheck;
/**
 * 회원 위치->픽업장소->카테고리->식당 의 순차적인 선택을 한 후
 * 글쓰기 전 선택 내역에 대한 최종 확인
 *
 * */
@Repository
@Transactional(readOnly = true)
public class OrderCheckRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public OrderCheckRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Transactional
    public void save(OrderCheck order){
        em.persist(order);
    }

    public OrderCheck findOne(Long id){
        return em.find(OrderCheck.class, id);
    }



    //주문상태, 특정 문자열을 포함하고 있는지 여부를 확인하여 반환
    public List<OrderCheck> findAll(OrderSearchDTO orderSearch){
        OrderStatus orderStatus = orderSearch.getOrderStatus();
        String nickName = orderSearch.getNickName();
        return query.select(orderCheck).from(orderCheck).where(memberIdContains(nickName),equalOrderStatus(orderStatus)).fetch();
    }


    private BooleanExpression equalOrderStatus(OrderStatus orderStatus) {
        return orderCheck.orderStatus.eq(orderStatus);
    }

    private BooleanExpression memberIdContains(String loginId) {
        return orderCheck.nickName.contains(loginId);
    }


}
