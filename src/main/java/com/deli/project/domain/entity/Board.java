package com.deli.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 게시판 테이블
 * */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderCheck order;
    private String localDateTime;
    private String title;
    private String loginId;

    private int likeCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public void setCount(int likeCount) {
        this.likeCount = likeCount;
    }


    private String content;

    public void setComments(Comment comment) {
        comments.add(comment);
        comment.setBoard(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrder(OrderCheck order) {
        order.setBoard(this);
        this.order = order;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static Board createBoard(OrderCheck order, String loginId,String title, String content){
       Board board = new Board();
        board.setOrder(order);
        board.setLoginId(loginId);
        board.setTitle(title);
        board.setContent(content);
        board.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss")));
       return board;
    }




}
