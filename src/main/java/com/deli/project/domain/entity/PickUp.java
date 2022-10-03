package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Coordinate coordinate;

    private boolean isShow;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public void setMember(Member member) {
        this.member = member;
    }



}
