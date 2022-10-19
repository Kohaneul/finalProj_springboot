package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveForm {

    //픽업장소,카테고리,장소명,주소,주문메뉴
    private String pickUpPlace;
    private String categoryName;
    private String restaurantName;
    private String address;
    private Long menuId;
    private String LoginId;

}
