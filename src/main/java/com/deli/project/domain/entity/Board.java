package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    private LocalDateTime localDateTime;
    private String title;

    private String content;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static Board createBoard(Order order, String title, String content){
       Board board = new Board();
        board.setOrder(order);
        board.setTitle(title);
        board.setContent(content);
       return board;
    }

}
