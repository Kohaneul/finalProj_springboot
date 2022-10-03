package com.deli.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Coordinate {
    private double latitude;
    private double longitude;
}
