package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.MemberSort;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.deli.project.domain.entity.QMember.member;
/**
 * 멤버 정보 저장소
 */
@Repository
@Slf4j
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

    public Member findId(String loginId){
        return findAll().stream().filter(m->m.getLoginId().equals(loginId)).findFirst().orElse(null);
    }

    public List<Member> findLoginId(String loginId){
        log.info("loginId={}",loginId);
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class).setParameter("loginId", loginId).getResultList();
    }

    public List<Member> findNickName(String nickName){
        return em.createQuery("select m from Member m where m.nickName = :nickName",Member.class).setParameter("nickName",nickName).getResultList();
    }

//특정 조건에 부합하는 회원 정보 조회
    public List<Member> findAll(MemberSearchDTO memberSearch){
        MemberSort memberSort = memberSearch.getMemberSort();
        String loginId = memberSearch.getLoginId();
        return query.select(member).from(member).where(containsLoginId(loginId),equalsMemberSort(memberSort)).fetch();
    }

    private BooleanExpression equalsMemberSort(MemberSort memberSort) {
        if(memberSort!=null){
            return member.memberSort.eq(memberSort);

        }
        return null;
    }

    private BooleanExpression containsLoginId(String loginId) {
        if(loginId!=null){
            return member.loginId.contains(loginId);
        }
        return null;
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }




}
