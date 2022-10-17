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

    @GetMapping("/myPosition")
    public String step1(@SessionAttribute(name= ConstEntity.USER_SESSION) Long memberSession,Model model){
        Member member = memberService.findOne(memberSession);   //로그인시 생성해두었던 USER_SESSION (MEMBER PK값) 조회
        model.addAttribute("member",member);
        return "/map/MapSearch";
    }

    //회원 주소와 픽업장소의 거리를 계산하고 VIEW로 보여줌
    @GetMapping("/pickUpPlace")
    public String step2(@RequestParam("lat")double myLat, @RequestParam("lon")double myLon, @SessionAttribute(name= ConstEntity.USER_SESSION) Long memberSession, Model model){
        //픽업장소의 경,위도와 Member의 경도, 위도를 계산
        List<CalculateDto> calculate = pickUpService.setArray(pickUpService.findAll(), myLat, myLon);
        //해당되는 pickup 장소를 테이블에 넣음
        List<PickUp> place = pickUpAdd(myLat,myLon,calculate,memberSession);
        model.addAttribute("cal",calculate);
        model.addAttribute("place",place);
//        model.addAttribute("myLat",myLat);
//        model.addAttribute("myLon",myLon);
        return "/pickup/PlaceSelect";
    }

    private List<PickUp> pickUpAdd(double myLat, double myLon, List<CalculateDto> calculate,@SessionAttribute(name= ConstEntity.USER_SESSION) Long memberSession) {
        log.info("calculate size={}",calculate.size());
        List<PickUp> list = new ArrayList<>();
        calculate.stream().forEach(c->list.add(pickUpService.findOne(c.getPickupId())));
        list.add(myPlaceAdd(myLat, myLon, memberSession));
        return list;
    }

    private PickUp myPlaceAdd(double myLat, double myLon, Long memberSession) {
        String loginId = memberService.findOne(memberSession).getLoginId();
        String address = memberService.findOne(memberSession).getAddress().getCity() +" " +memberService.findOne(memberSession).getAddress().getState();
        PickUp pick = new PickUp(loginId,address,new Coordinate(myLat, myLon));
        pickUpService.savePickUp(pick);
        return pick;
    }

}
