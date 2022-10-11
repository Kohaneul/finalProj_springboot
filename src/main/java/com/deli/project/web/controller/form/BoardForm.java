package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardForm {


    private Order order;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String pickUpName;
    @NotEmpty
    private String category;
    @NotEmpty
    private String restaurantName;
    @NotEmpty
    @Length(min = 5,max = 100)
    private String title;
    @NotEmpty
    @Length(min = 5,max = 500)
    private String content;

}
