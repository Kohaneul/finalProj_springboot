package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 댓글 테이블
 * */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    private String memberLoginId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setContent(String content) {
        this.content = content;
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"));
    }

    private String content;

    private String localDateTime;




    public Comment (String memberLoginId, Board board, String content){
        this.memberLoginId = memberLoginId;
        this.board = board;
        this.content = content;
        this.localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss"));

    }
}
