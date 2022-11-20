package com.deli.project.domain.service;

import com.deli.project.domain.entity.Coordinate;
import com.deli.project.domain.entity.PickUp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class PickUpServiceTest {

    @Autowired private PickUpService pickUpService;

    @Test
    @DisplayName("픽업장소 저장Test")
    void saveTest(){

        //픽업장소 저장 Logic
        List<PickUp> pickUpList1 = savePickUpPlace();

        //저장된 픽업장소 조회
        List<PickUp> pickUpAll = pickUpService.findAll();

        //저장한 픽업장소와 저장된 픽업장소의 배열 사이즈가 같은지 검증
        Assertions.assertThat(pickUpList1.size()).isEqualTo(pickUpAll.size());
    }

    @Test
    @DisplayName("픽업장소 오름차순 정렬 테스트")
    void arrayTest(){

        //픽업장소 저장
        List<PickUp> pickUpList1 = savePickUpPlace();

        Coordinate coordinate = new Coordinate(37.513379538688,126.92652335463);

        List<PickUp> pickUpList = pickUpService.findAll();
        List<CalculateDto> calculateDtos = pickUpService.setArray(pickUpList, coordinate.getLongitude(),coordinate.getLatitude());

        //비교행야하는 경,위도를 pickup 배열의 2번째 인덱스와 동일하게 설정하면 거리가 0으로 계산됨-> 0번째 인덱스로 조회됨
        //그러므로 pickUp 배열에서 2번째 PK 값과 calculateDtos의 0번째 인덱스 중 pickUpId가 동일한지 검증
        Assertions.assertThat(calculateDtos.get(0).getPickupId()).isSameAs(pickUpList.get(2).getId());

    }

    private List<PickUp> savePickUpPlace(){
        List<PickUp> pickUpList = new ArrayList<>();
        PickUp pickup1 = new PickUp("경복궁역","서울 종로구 사직로 지하 130", new Coordinate(37.57557082171,126.97330778814));
        PickUp pickup2 = new PickUp("광화문우체국","서울 종로구 종로 6", new Coordinate(37.570013917406,126.9780542555));
        PickUp pickup3 = new PickUp("대방역","서울 영등포구 여의대방로 300", new Coordinate(37.513379538688,126.92652335463));
        PickUp pickup4 = new PickUp("무악공원","서울 종로구 통일로18길 34", new Coordinate(37.576084140052,126.95911825248));


        savePickUp(pickUpService,pickup1,pickup2,pickup3,pickup4);


        pickUpList.add(pickup1);
        pickUpList.add(pickup2);
        pickUpList.add(pickup3);
        pickUpList.add(pickup4);

        return pickUpList;
    }

    private void savePickUp(PickUpService pickUpService,PickUp ... pickUps){
        Arrays.stream(pickUps).forEach(i->pickUpService.savePickUp(i));
    }


}