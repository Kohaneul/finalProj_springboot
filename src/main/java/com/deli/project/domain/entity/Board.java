package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
    private LocalDateTime localDateTime;
    private String title;

    private String content;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static Board createBoard(Member member, String title, String content){
       Board board = new Board();
        board.setMember(member);
        board.setTitle(title);
        board.setContent(content);
        board.setLocalDateTime(LocalDateTime.now());
       return board;
    }

}
