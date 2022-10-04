package com.deli.project.web.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class calculateForm {
    @NotNull
    private Long pickupId;
    @NotNull
    private double distance;
}
