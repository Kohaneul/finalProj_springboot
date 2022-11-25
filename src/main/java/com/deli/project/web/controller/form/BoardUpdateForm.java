package com.deli.project.web.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 수정 FORM
 *
 * */

@Getter @Setter
@NoArgsConstructor
public class BoardUpdateForm {
    @NotNull
    private Long boardId;   //주문내역
    private List<CommentSaveForm> comments = new ArrayList<>();  //댓글
    private void addCommentListForm(CommentSaveForm comment){
        comments.add(comment);
    }

    public BoardUpdateForm(Long boardId, CommentSaveForm comment) {
        this.boardId = boardId;
        this.addCommentListForm(comment);
    }
}
