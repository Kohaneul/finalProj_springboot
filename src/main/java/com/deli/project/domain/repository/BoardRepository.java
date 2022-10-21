package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Board;
import com.deli.project.domain.entity.Comment;
import com.deli.project.domain.service.BoardSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.deli.project.domain.entity.QBoard.board;

/**
 * 게시글 저장소
 * */
@Repository
@Transactional(readOnly = true)
public class BoardRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public BoardRepository(EntityManager em) {
        this.em = em;
        this.query=new JPAQueryFactory(em);
    }

    @Transactional
    public void save(Board board){
        em.persist(board);
    }

    @Transactional
    public void save(Comment comment){
        em.persist(comment);
    }

    public Board findOne(Long id){
        return em.find(Board.class,id);
    }

    public List<Board> findAll(){
        return em.createQuery("select b from Board b",Board.class).getResultList();
    }
    //게시글 조회 => 게시글 제목에 특정 숫자가 들어있는지 , 게시글 제목에 로그인 아이디가 포함되어있는지 여부에 따라서 조회
    public List<Board> findAll(BoardSearchDto boardSearchDto){
        String loginId = boardSearchDto.getLoginId();
        String title = boardSearchDto.getTitle();
        return query.select(board).from(board).where(containsLoginId(loginId),containsTitle(title)).fetch();
    }

    private BooleanBuilder containsTitle(String title) {
        if(title==null){
        return new BooleanBuilder();
        }
        return new BooleanBuilder(board.title.contains(title));
    }

    private BooleanBuilder containsLoginId(String loginId) {
        if(loginId==null){
            return new BooleanBuilder();
        }
        return new BooleanBuilder(board.order.loginId.eq(loginId));
    }



}
