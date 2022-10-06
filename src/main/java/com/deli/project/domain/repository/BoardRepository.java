package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Board;
import com.deli.project.domain.entity.QBoard;
import com.deli.project.domain.service.BoardSearchDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.deli.project.domain.entity.QBoard.board;


@Repository
public class BoardRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public BoardRepository(EntityManager em) {
        this.em = em;
        this.query=new JPAQueryFactory(em);
    }

    public void save(Board board){
        em.persist(board);
    }

    public Board findOne(Long id){
        return em.find(Board.class,id);
    }

    public List<Board> findAll(){
        return em.createQuery("select b from Board b",Board.class).getResultList();
    }

    public List<Board> findAll(BoardSearchDto boardSearchDto){
        String loginId = boardSearchDto.getLoginId();
        String title = boardSearchDto.getTitle();
        return query.select(board).from(board).where(containsLoginId(loginId),containsTitle(title)).fetch();
    }

    private BooleanExpression containsTitle(String title) {
        return board.title.contains(title);
    }

    private BooleanExpression containsLoginId(String loginId) {
        return board.member.loginId.contains(loginId);
    }



}
