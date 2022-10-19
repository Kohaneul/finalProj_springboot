package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 픽업 장소 테이블
 * */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PickUp {
    @Id
    @GeneratedValue
    @Column(name="pickup_id")
    private Long id;

    @Column(name="place_name")
    private String placeName;

    @Embedded
    private Coordinate coordinate;  //좌표 : 경도, 위도의 공통 속성을 묵음

    @OneToMany(mappedBy = "pickUp")
    private List<Category> category = new ArrayList<>();

    private String address;


    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public PickUp(String placeName,String address, Coordinate coordinate){
        this.placeName = placeName;
        this.address = address;
        this.coordinate = coordinate;
    }



}
