package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.Category;
import com.deli.project.domain.entity.PickUp;
import com.deli.project.domain.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class RestaurantSaveForm {

    //픽업장소,카테고리,장소명,주소
    private PickUp pickUp;
    private Category category;
    private Restaurant restaurant;
    private Address address;

}
