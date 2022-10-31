package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 주문내역 저장하는 FORM
 *
 * */


@Getter @Setter
@AllArgsConstructor
public class OrderSaveForm {

    private String pickUpPlace;
    private String categoryName;
    private String restaurantName;
    private String address;
    private List<Long> chooseMenuIds;
    private String LoginId;
    private int totalPrice;

}