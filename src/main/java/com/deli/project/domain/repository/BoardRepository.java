package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Board;
import com.deli.project.domain.entity.Comment;
import com.deli.project.domain.entity.QOrderCheck;
import com.deli.project.domain.service.BoardSearchDto;
import com.deli.project.web.controller.form.BoardLikeForm;
import com.deli.project.web.controller.form.CommentUpdateForm;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.deli.project.domain.entity.QBoard.board;

/**
 * 게시글 저장소
 * */
@Repository
@Slf4j
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
    public void saveComment(Long id, Comment comment){
        Board board = findOne(id);
        board.setComments(comment);
    }

    public Comment findComment(Long commentId){
        return em.find(Comment.class,commentId);
    }

    public Comment findBoardInComment(Long boardId, Long commentId){
        Board board = findOne(boardId);
        Comment comment = board.getComments().stream().filter(c -> c.equals(findComment(commentId))).findFirst().orElse(null);
        return comment;
    }
    @Transactional
    public void updateComment(Long commentId, CommentUpdateForm commentUpdateForm){
        Comment comment = findComment(commentId);
        comment.setContent(commentUpdateForm.getContent());
    }

    @Transactional
    public void likeCount(BoardLikeForm boardLikeForm, Map<String,Integer> likeMap){
        String str = boardLikeForm.getCommentLoginId()+"&"+boardLikeForm.getBoardId();
        Board board = findOne(boardLikeForm.getBoardId());
        String loginId = board.getOrder().getLoginId();
        if(loginId.equals(boardLikeForm.getCommentLoginId())||likeMap.get(str)!=null){
            return;
        }
        else{
            int likeCount = board.getLikeCount();
            board.setCount(likeCount+1);
            likeMap.put(str,0);
        }
    }

    @Transactional
    public void deleteComment(Long commentId){
        Comment comment = findComment(commentId);
        em.remove(comment);
    }

    public Board findOne(Long id){
        return em.find(Board.class,id);
    }


    public List<Board> findAll(){
        return em.createQuery("select b from Board b fetch join b.order",Board.class).getResultList();
    }

    //게시글 조회 => 게시글 제목에 특정 숫자가 들어있는지 , 게시글 제목에 로그인 아이디가 포함되어있는지 여부에 따라서 조회
    public List<Board> findAll(BoardSearchDto boardSearchDto){
        String loginId = boardSearchDto.getLoginId();
        String title = boardSearchDto.getTitle();
        return query.select(board).from(board).where(containsLoginId(loginId),containsTitle(title)).fetch();
    }

    private BooleanExpression containsTitle(String title) {
        if(title!=null){
            return board.title.contains(title);
        }
        return null;
    }

    private BooleanExpression containsLoginId(String loginId) {
        if(loginId!=null){
            return board.loginId.contains(loginId);
        }
        return null;
    }



}
