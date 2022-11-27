package com.deli.project.web.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 댓글 작성 FORM
 *
 * */

@Getter @Setter
@Slf4j
@NoArgsConstructor
public class CommentUpdateForm {
    @NotNull
    private Long id; //댓글 pk번호
    @NotEmpty
    private String content; //내용


    public void setContent(String content) {
        this.content = content;

    }

    public CommentUpdateForm(Long id, String content) {
        this.id = id;
        this.content = content;

    }
}
