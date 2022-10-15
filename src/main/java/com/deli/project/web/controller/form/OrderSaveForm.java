package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
public class OrderSaveForm {
    private Restaurant restaurant;
    private PickUp pickUp;
    private Menu menu;

}
