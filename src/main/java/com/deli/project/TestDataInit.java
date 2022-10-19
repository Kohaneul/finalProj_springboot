package com.deli.project;

import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.deli.project.domain.entity.MemberSort.ADMIN;
import static com.deli.project.domain.entity.MemberSort.BASIC;

@Component
@Slf4j
public class TestDataInit {

    @Autowired  private MemberService memberService;
    @Autowired  private PickUpService pickUpService;
    @Autowired private CategoryService categoryService;
    @Autowired private RestaurantService restaurantService;

    @Autowired private MenuRepository menuRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void dataInit(){
        ImageFile uploadFile1 = new ImageFile("smile3.jpg","7dc3acd3-7db0-41ee-b564-0a7e9996a1e0.jpg");
        ImageFile uploadFile2 = new ImageFile("smile3.jpg","19dc57db-776d-4350-9c25-45847152644d.jpg");

        ImageFile uploadFile3 = new ImageFile("smile3.jpg","30abe9c8-86e1-400a-9e97-e7442c075705.jpg");

        ImageFile uploadFile4 = new ImageFile("smile3.jpg","56963f74-76fa-4843-a1f9-066844b2e34d.jpg");



        Member memberA = Member.createMember("memberA","1111","memberA","010-1111-2222", new Address("서울시","영등포구 신길동","123-456"),uploadFile1, ADMIN);
        Member memberB = Member.createMember("memberB","2222","memberB","010-2222-2222", new Address("서울시","종로구 사직동","789-456"),uploadFile2, BASIC);
        Member memberC = Member.createMember("memberC","3333","memberC","010-3333-3333", new Address("서울시","종로구 무악동","1111-5544"),uploadFile3, ADMIN);
        Member memberD = Member.createMember("memberD","4444","memberF","010-4444-4444", new Address("서울시","중구 다산동","1111-5544"),uploadFile4,BASIC);

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
        PickUp pickup16 = new PickUp("서울지방병무청역","서울 영등포구 여의대방로43길 13", new Coordinate(37.5055066,126.9190053));
        PickUp pickup17 = new PickUp("약수역","서울 중구 다산로 지하 122", new Coordinate(37.554124490755,127.01025330126));
       PickUp pickup18 = new PickUp("장원중학교","서울 중구 동호로15길 93-34", new Coordinate(37.55365335367,127.00627186623));
       PickUp pickup19 = new PickUp("정부청사","서울 종로구 세종대로 209", new Coordinate(36.4383354,126.9018892));
       PickUp pickup20 = new PickUp("후암초등학교","서울 용산구 두텁바위로 140", new Coordinate(37.551405239218,126.98159679014));
        PickUp pickup21 = new PickUp("신길3동주민센터","서울 영등포구 신길로41라길 13-8", new Coordinate(37.50744297731903,126.9079549785158));

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
        pickUpService.savePickUp(pickup21);


        Category category1 = new Category("돈까스·회·일식");
        Category category2 = new Category("중식");
        Category category3 = new Category("치킨");
        Category category4 = new Category("백반·죽·국수");
        Category category5 = new Category("카페·디저트");
        Category category6 = new Category("분식");
        Category category7 = new Category("찜·탕·찌개");
        Category category8 = new Category("피자");
        Category category9 = new Category("양식");
        Category category10 = new Category("고기·구이");
        Category category11= new Category("족발·보쌈");
        Category category12= new Category("아시안");
        Category category13= new Category("패스트푸드");
        Category category14= new Category("도시락");

        categoryService.saveCategory(category1);
        categoryService.saveCategory(category2);
        categoryService.saveCategory(category3);
        categoryService.saveCategory(category4);
        categoryService.saveCategory(category5);
        categoryService.saveCategory(category6);
        categoryService.saveCategory(category7);
        categoryService.saveCategory(category8);
        categoryService.saveCategory(category9);
        categoryService.saveCategory(category10);
        categoryService.saveCategory(category11);
        categoryService.saveCategory(category12);
        categoryService.saveCategory(category13);
        categoryService.saveCategory(category14);


        Restaurant restaurant1 = Restaurant.setRestaurant("A 돈까스",new Address("서울","영등포구 신길로 10"),category1,4.9,15000);
        Restaurant restaurant2 = Restaurant.setRestaurant("B 돈까스",new Address("서울","영등포구 영등포로 410"),category1,4.5,20000);
        Restaurant restaurant3 = Restaurant.setRestaurant("C 돈까스",new Address("서울","영등포구 신풍로25길 6 1층"),category1,3.3,30000);
        Restaurant restaurant4 = Restaurant.setRestaurant("D 돈까스",new Address("서울","영등포구 신길동 95-254"),category1,4.8,18000);

        Restaurant restaurant5 = Restaurant.setRestaurant("A 중식집",new Address("서울","영등포구 여의대방로 137"),category2,4.2,20000);
        Restaurant restaurant6 = Restaurant.setRestaurant("B 중식집",new Address("서울","영등포구 신길로39길 1"),category2,4.5,25000);
        Restaurant restaurant7 = Restaurant.setRestaurant("C 중식집",new Address("서울","영등포구 신풍로25길 6 1층"),category2,4.4,18000);
        Restaurant restaurant8 = Restaurant.setRestaurant("D 중식집",new Address("서울","영등포구 여의대방로 137"),category2,4.0,30000);

        Menu menu1 = new Menu("돈까스 정식 A",10000,restaurant1);
        Menu menu2 = new Menu("돈까스 정식 B",13000,restaurant1);
        Menu menu3 = new Menu("치즈돈까스",11000,restaurant1);
        Menu menu4 = new Menu("우동",7000,restaurant1);
        Menu menu5 = new Menu("냉모밀",7000,restaurant1);

        restaurantService.saveRestaurant(restaurant1);
        restaurantService.saveRestaurant(restaurant2);
        restaurantService.saveRestaurant(restaurant3);
        restaurantService.saveRestaurant(restaurant4);
        restaurantService.saveRestaurant(restaurant5);
        restaurantService.saveRestaurant(restaurant6);
        restaurantService.saveRestaurant(restaurant7);
        restaurantService.saveRestaurant(restaurant8);


        menuRepository.save(menu1);
        menuRepository.save(menu2);
        menuRepository.save(menu3);
        menuRepository.save(menu4);
        menuRepository.save(menu5);



    }
}
