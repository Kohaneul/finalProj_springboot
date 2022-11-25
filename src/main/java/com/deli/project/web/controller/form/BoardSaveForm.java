package com.deli.project.web.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시글 작성 FORM
 *
 * */

@Getter @Setter
@NoArgsConstructor
public class BoardSaveForm {
    @NotNull
    private Long orderId;   //주문내역
    @NotEmpty
    private String nickName;    //작성자 닉네임
    @NotEmpty
    private String pickUpName;  //픽업장소
    @NotEmpty
    private String category;    //음식 카테고리
    @NotEmpty
    private String restaurantName;  //주문할 식당

    @Length(min = 5,max = 100)
    private String title;   //게시글 제목

    @Length(min = 5,max = 500)
    private String content; //게시글 내용
    @NotNull
    private int minOrderPrice;  //최소주문금액


    public BoardSaveForm(Long orderId, String nickName, String pickUpName, String category, String restaurantName, int minOrderPrice) {
        this.orderId = orderId;
        this.nickName = nickName;
        this.pickUpName = pickUpName;
        this.category = category;
        this.restaurantName = restaurantName;
        this.minOrderPrice = minOrderPrice;
    }
}
