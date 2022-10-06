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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/board/select")
public class MapController {

    private final MemberService memberService;
    private final PickUpService pickUpService;

    @GetMapping("/step_1")
    public String step1(@SessionAttribute(name= ConstEntity.USER_SESSION) Long memberSession,Model model,HttpServletRequest request){
        Member member = memberService.findOne(memberSession);
        HttpSession session = request.getSession();
        session.setAttribute(ConstEntity.USER_SESSION,member.getId());
        log.info("userSession={}",session.getAttribute(ConstEntity.USER_SESSION));
        model.addAttribute("member",member);
        return "/map/MapSearch";
    }

    @PostMapping("/step_1")
    public String step1(@RequestParam("lat")double myLat,@RequestParam("lon")double myLon,@SessionAttribute(name= ConstEntity.USER_SESSION) Long memberSession,Model model){
        List<CalculateDto> calculate = pickUpService.setArray(pickUpService.findAll(), myLat, myLon);

        PickUp pick = myPlaceAdd(myLat, myLon, memberSession);
        calculate.add(new CalculateDto(pick.getId(),pick.getPlaceName(),0.0));
        List<PickUp> place = pickUpAdd(calculate);
        model.addAttribute("cal",calculate);
        model.addAttribute("place",place);
        return "redirect:/board/select/step_2";
    }

    @GetMapping("/step_2")
    public String step2(){
        return "/map/PlaceSelect";
    }

    private List<PickUp> pickUpAdd(List<CalculateDto> calculate) {
        List<PickUp> list = new ArrayList<>();
        for (CalculateDto calculateDto : calculate) {
            list.add(pickUpService.findOne(calculateDto.getPickupId()));
        }
        return list;
    }

    private PickUp myPlaceAdd(double myLat, double myLon, Long memberSession) {
        String loginId = memberService.findOne(memberSession).getLoginId();
        String address = memberService.findOne(memberSession).getAddress().getCity() + memberService.findOne(memberSession).getAddress().getState();
        PickUp pick = new PickUp(loginId,address,new Coordinate(myLat, myLon));
        pickUpService.savePickUp(pick);
        return pick;
    }

}
