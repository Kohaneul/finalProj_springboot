package com.deli.project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
/**
 * 속성 : 주소
 * */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    private String city;    // 시,도,군,구
    private String state;   // 상세주소
    private String zipCode; // 우편번호
    public Address(String city, String state){
        this.city = city;
        this.state = state;
    }
}
