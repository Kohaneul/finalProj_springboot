package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    private double score;

    public void setBoard(Board board) {
        this.board = board;
    }

    public Comment (String memberLoginId, String content, double score){
        this.memberLoginId = memberLoginId;
        this.content = content;
        this.score = score;
        this.localDateTime = LocalDateTime.now();
    }
}
