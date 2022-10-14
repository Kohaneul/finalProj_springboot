package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.OrderCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCheckForm {
   @NotEmpty
    private OrderCheck order;
    @NotEmpty
    @Length(min = 5,max = 100)
    private String title;
    @NotEmpty
    @Length(min = 5,max = 500)
    private String content;

    public void setOrder(OrderCheck order) {
        this.order = order;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
