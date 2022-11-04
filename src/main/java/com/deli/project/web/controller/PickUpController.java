package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Coordinate;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.PickUp;
import com.deli.project.domain.service.CalculateDto;
import com.deli.project.domain.service.MemberService;
import com.deli.project.domain.service.PickUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 픽업 장소 관련 컨트롤러
*/
@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/board/select")
public class PickUpController {

    private final MemberService memberService;
    private final PickUpService pickUpService;

    private Long userSession = 0L;

    @GetMapping("/myPosition")
    public String step1(@SessionAttribute(name= ConstEntity.USER_SESSION) Long memberSession,Model model){
        userSession = memberSession;
        Member member = memberService.findOne(memberSession);   //로그인시 생성해두었던 USER_SESSION (MEMBER PK값) 조회
        model.addAttribute("member",member);
        return "/map/MapSearch";
    }

    //회원 주소와 픽업장소의 거리를 계산하고 VIEW 로 보여줌
    @GetMapping("/pickUpPlace")
    public String step2(@RequestParam("lat")double myLat, @RequestParam("lon")double myLon, Model model){
        //픽업장소의 경,위도와 Member의 경도, 위도를 계산하여 반경 1km 이내에 있는 곳 추출
        List<CalculateDto> calculate = pickUpService.setArray(pickUpService.findAll(), myLat, myLon);
        //해당되는 pickup 장소를 테이블에 넣음
        List<PickUp> place = pickUpAdd(myLat,myLon,calculate,userSession);
        model.addAttribute("cal",calculate);
        model.addAttribute("place",place);
        return "/pickup/PlaceSelect";
    }

    private List<PickUp> pickUpAdd(double myLat, double myLon, List<CalculateDto> calculate,Long memberSession) {
        List<PickUp> list = new ArrayList<>();
        calculate.stream().forEach(c->list.add(pickUpService.findOne(c.getPickupId())));
        list.add(myPlaceAdd(myLat, myLon, memberSession));
        return list;
    }

    private PickUp myPlaceAdd(double myLat, double myLon, Long memberSession) {
        Member member = memberService.findOne(memberSession);
        String loginId = member.getLoginId();
        PickUp pick = new PickUp(loginId,member.getAddress().getCity() +" " +member.getAddress().getState(),new Coordinate(myLat, myLon));
        pickUpService.savePickUp(pick);
        return pick;
    }

}
