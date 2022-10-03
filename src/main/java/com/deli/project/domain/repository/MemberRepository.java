package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.QMember;
import com.deli.project.domain.repository.dto.DeliverySameDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MemberRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public MemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from member m").getResultList();
    }



}
