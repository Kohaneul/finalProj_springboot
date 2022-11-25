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
@NoArgsConstructor
public class CommentSaveForm {

    private Long commentId; //댓글 pk번호

    @NotEmpty
    private String content; //내용


    @NotNull
    @DateTimeFormat(pattern = "MM/dd HH:mm")
    private LocalDateTime localDateTime;    //작성시간


    public CommentSaveForm(String content) {
        this.content = content;
        this.localDateTime = LocalDateTime.now();
    }
}
