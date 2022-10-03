package com.deli.project.domain.entity;

import lombok.AllArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
public class Address {
    private String city;
    private String state;
    private String zipCode;
}
