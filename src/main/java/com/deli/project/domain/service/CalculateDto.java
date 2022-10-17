package com.deli.project.domain.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 사용자에게 반환된 경, 위도 값을 거리계산하여
 view에 반환되어 보여주기 위한 DTO
 */
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
