package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.Comment;
import com.deli.project.domain.entity.OrderCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 댓글 작성 FORM
 *
 * */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    @Length(min = 5,max = 500)
    private String content;

    @NotEmpty
    private String restaurantName;

    @NotNull
    private LocalDateTime localDateTime;

    public CommentForm(String loginId, String content, String restaurantName) {
        this.loginId = loginId;
        this.content = content;
        this.restaurantName = restaurantName;
        this.localDateTime = LocalDateTime.now();
    }
}
