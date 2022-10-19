package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * 식당 정보 저장시 사용되는 FORM
 *
 * */
@Getter @Setter
@AllArgsConstructor
public class RestaurantSaveForm {

    private PickUp pickUp;
    private Category category;
    private Restaurant restaurant;
    private Address address;

}
