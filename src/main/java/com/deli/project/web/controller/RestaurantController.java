package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.repository.RestaurantDTO;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.OrderSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.deli.project.domain.ConstEntity.*;
/**
 * 식당 관련 컨트롤러
 */

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/select")
public class RestaurantController {
    private final MemberService memberService;
    private final OrderCheckRepository orderCheckRepository;
    private final RestaurantService restaurantService;
    private final CategoryService categoryService;
    private final PickUpService pickUpService;
    private final MenuRepository menuRepository;



    //선택한 카테고리와 일치하는 식당 정보를 보여줌
    @GetMapping("/restaurant")
    public String resChoose(@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId, HttpSession session, Model model){
        //선택한 카테고리 관련 pk 값은 세션에 저장
        sessionSave(session, CATEGORY_SESSION,categoryId);
        PickUp pickUp = pickUpService.findOne(pickupId);
        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDTO(categoryId, pickUp.getAddress()));
        model.addAttribute("restaurants",restaurants);
        model.addAttribute("category",categoryService.findOne(categoryId));
        model.addAttribute("pickUp",pickUp);
        return "/restaurant/RestaurantSelect";
    }


    private void sessionSave(HttpSession session, String CONST_ENTITY,Long id) {
        session.setAttribute(CONST_ENTITY, id);
    }

    //선택한 카테고리와 일치하는 식당 정보를 보여줌
    @PostMapping("/restaurant")
    public String resChoose(@RequestParam("restaurantId")Long restaurantId, RedirectAttributes redirectAttributes){
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        //선택한 식당 관련 pk 값은 세션에 저장
        redirectAttributes.addAttribute("restaurantId", restaurant.getId());
        return "redirect:/board/select/{restaurantId}/menu";
    }

    //선택한 식당정보에 속하는 메뉴 정보를 보여줌
    @GetMapping("/{restaurantId}/menu")
    public String chooseMenu(HttpSession session,@PathVariable Long restaurantId,Model model){
        session.setAttribute(RESTAURANT_SESSION, restaurantId);
        List<Menu> menuList = menuRepository.findMenuList(restaurantId);
        model.addAttribute("menuList",menuList);
        String restaurantName= restaurantService.findOne(restaurantId).getRestaurantName();
        model.addAttribute("restaurantName",restaurantName);
        return "/restaurant/MenuSelect";
    }


}