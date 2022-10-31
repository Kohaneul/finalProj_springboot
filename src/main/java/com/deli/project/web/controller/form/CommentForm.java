package com.deli.project.web.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 댓글 작성 FORM
 *
 * */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {

    @NotEmpty
    private String loginId; //작성자 아이디

    @NotEmpty
    @Length(min = 5,max = 500)
    private String content; //내용

    @NotNull
    private Long boardId;  //게시글 pk번호

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;    //작성시간

    public CommentForm(String loginId, String content,Long boardId, String restaurantName) {
        this.loginId = loginId;
        this.content = content;
        this.boardId = boardId;
        this.localDateTime = LocalDateTime.now();
    }
}
