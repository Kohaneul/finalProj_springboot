package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardForm {
   @NotEmpty
    private Order order;
    @NotEmpty
    @Length(min = 5,max = 100)
    private String title;
    @NotEmpty
    @Length(min = 5,max = 500)
    private String content;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
