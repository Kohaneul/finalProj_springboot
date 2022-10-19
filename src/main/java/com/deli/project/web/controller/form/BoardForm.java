package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.OrderCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 게시글 작성 FORM
 *
 * */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardForm {
    @NotNull
    private OrderCheck order;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String pickUpName;
    @NotEmpty
    private String category;
    @NotEmpty
    private String restaurantName;
    @Length(min = 5,max = 100)
    private String title;

    @Length(min = 5,max = 500)
    private String content;
    @NotNull
    private int minOrderPrice;

    public BoardForm(OrderCheck order, String nickName, String pickUpName, String category, String restaurantName, int minOrderPrice) {
        this.order = order;
        this.nickName = nickName;
        this.pickUpName = pickUpName;
        this.category = category;
        this.restaurantName = restaurantName;
        this.minOrderPrice = minOrderPrice;
    }
}
