package com.deli.project.web.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private String localDateTime;

    public CommentSaveForm(String content) {
        this.content = content;
    }
}
