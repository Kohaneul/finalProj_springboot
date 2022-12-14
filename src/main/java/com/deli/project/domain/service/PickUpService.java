package com.deli.project.domain.service;

import com.deli.project.domain.entity.PickUp;
import com.deli.project.domain.repository.PickUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * PickUp 저장소를 주입 받아 저장, 조회 기능을 실행하기 위한 서비스 함수.
 *
 * */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PickUpService {
    private final PickUpRepository repository;
    @Transactional
    public Long savePickUp(PickUp pickUp){
        repository.save(pickUp);
        return pickUp.getId();
    }


    public PickUp findOne(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<PickUp> findAll(){
        return repository.findAll();
    }

    //GeoCoder를 통해서 사용자의 위치를 경도, 위도 주소값으로 반환받아 거리를 계산해주는 기능
    public List<CalculateDto> setArray(List<PickUp> place, double lat, double lon) {
        List<CalculateDto> cal = new ArrayList<>();
        for (PickUp pickUp : place) {

            double distance = disCal(pickUp.getCoordinate().getLatitude(),pickUp.getCoordinate().getLongitude(),lon,lat);
            if (distance < 1000) {  //거리가 1km 미만이라면
                CalculateDto calculat = new CalculateDto(pickUp.getId(), pickUp.getPlaceName(), pickUp.getAddress(), distance); //CalculateDto 객체 생성하여
                cal.add(calculat);//넣는다.
            }
        }
        sortList(cal);// 거리가 가까운 순으로 정렬하는 메소드
        return cal;
    }

    private void sortList(List<CalculateDto> obj) {
        Collections.sort(obj, new Comparator<CalculateDto>(){
            @Override
            public int compare(CalculateDto o1, CalculateDto o2) {
                return o1.getDistance() < o2.getDistance()?-1:1;
            }
        });

    }

    private double disCal(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1609.344;
        dist = Double.parseDouble(cutDecimal(2, dist));
        return dist;
    };

    private String cutDecimal(int cutSize, double value) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(cutSize);
        nf.setGroupingUsed(false);

        return nf.format(value);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    };

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    };






}
