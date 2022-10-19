package com.deli.project.web.controller;

import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.repository.RestaurantDTO;
import com.deli.project.domain.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
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
        //선택한 식당 관련 pk 값은 세션에 저장
        sessionSave(session, CATEGORY_SESSION,categoryId);  //선택한 카테고리 pk값 세션에 저장
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


    @PostMapping("/restaurant")
    public String resChoose(HttpSession session,@RequestParam("restaurantId")Long restaurantId, RedirectAttributes redirectAttributes){
        sessionSave(session, RESTAURANT_SESSION,restaurantId);
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        redirectAttributes.addAttribute("restaurantId", restaurant.getId());
        return "redirect:/board/select/{restaurantId}/menu";
    }

    @GetMapping("/{restaurantId}/menu")
    public String chooseMenu(@PathVariable Long restaurantId,Model model){
        List<Menu> menuList = menuRepository.findMenuList(restaurantId);
        model.addAttribute("menuList",menuList);
       String restaurantName= restaurantService.findOne(restaurantId).getRestaurantName();
        model.addAttribute("restaurantName",restaurantName);
        return "/restaurant/MenuSelect";
    }

    @PostMapping("/{restaurantId}/menu")
    public String chooseMenu2(@PathVariable Long restaurantId,@RequestParam Long menuId, Model model){
        Menu menu = menuRepository.findOne(menuId);
        model.addAttribute("menu",menu);
        return "redirect:/board/select";
    }


}
