package com.deli.project.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CalculateDto {
    @NotNull
    private Long pickupId;
    @NotEmpty
    private String placeName;
    @NotEmpty
    private String address;
    @NotNull
    private double distance;
}
