package com.deli.project;

import com.deli.project.domain.entity.*;
import com.deli.project.domain.service.MemberService;
import com.deli.project.domain.service.PickUpService;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.deli.project.domain.entity.MemberSort.ADMIN;
import static com.deli.project.domain.entity.MemberSort.BASIC;

@Component
public class TestDataInit {

    @Autowired  private MemberService memberService;
    @Autowired  private PickUpService pickUpService;

    @EventListener(ApplicationReadyEvent.class)
    public void dataInit(){
        Member memberA = Member.createMember("memberA","1111","memberA","010-1111-2222", BASIC,new Address("서울시","영등포구 신길동","123-456"),null);
        Member memberB = Member.createMember("memberB","2222","memberB","010-2222-2222", BASIC,new Address("서울시","종로구 사직동","789-456"),null);
        Member memberC = Member.createMember("memberC","3333","memberC","010-3333-3333", ADMIN,new Address("서울시","종로구 무악동","1111-5544"),null);
        Member memberD = Member.createMember("memberD","4444","memberF","010-4444-4444", BASIC,new Address("서울시","중구 다산동","1111-5544"),null);

        memberService.saveMember(memberA);
        memberService.saveMember(memberB);
        memberService.saveMember(memberC);
        memberService.saveMember(memberD);

        
       PickUp pickup1 = new PickUp("경복궁역","서울 종로구 사직로 지하 130", new Coordinate(37.57557082171,126.97330778814));
       PickUp pickup2 = new PickUp("광화문우체국","서울 종로구 종로 6", new Coordinate(37.570013917406,126.9780542555));
       PickUp pickup3 = new PickUp("대방역","서울 영등포구 여의대방로 300", new Coordinate(37.513379538688,126.92652335463));
       PickUp pickup4 = new PickUp("무악공원","서울 종로구 통일로18길 34", new Coordinate(37.576084140052,126.95911825248));
       PickUp pickup5 = new PickUp("무악재역","서울 서대문구 통일로 지하 361", new Coordinate(37.582561834301,126.95017035418));
       PickUp pickup6 = new PickUp("버티고개역","서울 중구 다산로 지하 38", new Coordinate(37.547943570723,127.00697726349));
       PickUp pickup7 = new PickUp("보라매역","서울 동작구 상도로 지하 2", new Coordinate(37.499855465771,126.92060923706));
       PickUp pickup8 = new PickUp("사직단","서울 종로구 사직로 89", new Coordinate(37.575791319905,126.96870586958));
       PickUp pickup9 = new PickUp("삼광초등학교","서울 용산구 두텁바위로1나길 19", new Coordinate(37.546646838565,126.9766344795));
       PickUp pickup10 = new PickUp("서대문역","서울 종로구 통일로 지하 126", new Coordinate(37.574379813301,126.95782423784));
       PickUp pickup11 = new PickUp("서울시청","서울 중구 세종대로 110", new Coordinate(37.566205021936,126.97770627907));
       PickUp pickup12 = new PickUp("세종문화회관","서울 종로구 세종대로 175", new Coordinate(37.572009887822,26.97630706472));
       PickUp pickup13 = new PickUp("숙대입구역","서울 용산구 한강대로 지하 306", new Coordinate(37.545499859161,126.97216591319));
       PickUp pickup14 = new PickUp("신길5동우체국","서울 영등포구 신길로 65", new Coordinate(37.498477701477,126.90806625532));
       PickUp pickup15 = new PickUp("신길역","서울 영등포구 영등포로 327", new Coordinate(37.517688232974,126.91427854285));
       PickUp pickup16 = new PickUp("신풍역","서울 영등포구 신풍로 지하 27", new Coordinate(37.50016518673,126.90903453451));
       PickUp pickup17 = new PickUp("약수역","서울 중구 다산로 지하 122", new Coordinate(37.554124490755,127.01025330126));
       PickUp pickup18 = new PickUp("장원중학교","서울 중구 동호로15길 93-34", new Coordinate(37.55365335367,127.00627186623));
       PickUp pickup19 = new PickUp("정부청사","서울 종로구 세종대로 209", new Coordinate(36.4383354,126.9018892));
       PickUp pickup20 = new PickUp("후암초등학교","서울 용산구 두텁바위로 140", new Coordinate(37.551405239218,126.98159679014));

        pickUpService.savePickUp(pickup1);
        pickUpService.savePickUp(pickup2);
        pickUpService.savePickUp(pickup3);
        pickUpService.savePickUp(pickup4);
        pickUpService.savePickUp(pickup5);
        pickUpService.savePickUp(pickup6);
        pickUpService.savePickUp(pickup7);
        pickUpService.savePickUp(pickup8);
        pickUpService.savePickUp(pickup9);
        pickUpService.savePickUp(pickup10);
        pickUpService.savePickUp(pickup11);
        pickUpService.savePickUp(pickup12);
        pickUpService.savePickUp(pickup13);
        pickUpService.savePickUp(pickup14);
        pickUpService.savePickUp(pickup15);
        pickUpService.savePickUp(pickup16);
        pickUpService.savePickUp(pickup17);
        pickUpService.savePickUp(pickup18);
        pickUpService.savePickUp(pickup19);
        pickUpService.savePickUp(pickup20);




    }
}
