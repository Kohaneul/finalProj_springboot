package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.repository.RestaurantDTO;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.RestaurantSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

//    @GetMapping("/restaurant")
//    public String resChoose(@ModelAttribute("saveForm")RestaurantSaveForm saveForm,@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId, HttpServletRequest request, Model model){
//        HttpSession session = request.getSession();
//        session.setAttribute(ConstEntity.CATEGORY_SESSION,categoryId);  //카테고리 정보 세션에 저장
//        saveForm.setCategory(categoryService.findOne(categoryId));  //
//        PickUp pickUp = pickUpService.findOne(pickupId);
//        saveForm.setPickUp(pickUp);
//        saveForm.setAddress(new Address(pickUp.getAddress().split(" ")[0],pickUp.getAddress().split(" ")[1]));
//        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDTO(categoryId, pickUp.getAddress()));
//        model.addAttribute("restaurants",restaurants);
//        model.addAttribute("pickUp",pickUp);
//        return "/restaurant/RestaurantSelect";
//    }


    //선택한 카테고리와 일치하는 식당 정보를 보여줌
    @GetMapping("/restaurant")
    public String resChoose(@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId, HttpServletRequest request, Model model){
        sessionSave(request, CATEGORY_SESSION,categoryId);  //선택한 카테고리 pk값 세션에 저장
        PickUp pickUp = pickUpService.findOne(pickupId);
        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDTO(categoryId, pickUp.getAddress()));
        model.addAttribute("restaurants",restaurants);
        model.addAttribute("pickUp",pickUp);
        return "/restaurant/RestaurantSelect";
    }


//    @PostMapping("/restaurant")
//    public String resChoose(HttpServletRequest request,@RequestParam("restaurantId")Long restaurantId,@Valid @ModelAttribute("saveForm")RestaurantSaveForm saveForm, BindingResult bindingResult,RedirectAttributes redirectAttributes){
//        if(bindingResult.hasErrors()){
//           return "/restaurant/RestaurantSelect";
//        }
//        HttpSession session = request.getSession();
//        session.setAttribute(RESTAURANT_SESSION,restaurantId);
//        Restaurant restaurant = restaurantService.findOne(restaurantId);
//
//        redirectAttributes.addAttribute("restaurantId", restaurant.getId());
//        return "redirect:/board/select/{restaurantId}/menu";
//    }


    @PostMapping("/restaurant")
    public String resChoose(HttpServletRequest request,@RequestParam("restaurantId")Long restaurantId, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/restaurant/RestaurantSelect";
        }
        sessionSave(request, RESTAURANT_SESSION,restaurantId);
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        redirectAttributes.addAttribute("restaurantId", restaurant.getId());
        return "redirect:/board/select/{restaurantId}/menu";
    }

    private void sessionSave(HttpServletRequest request, String CONST_ENTITY,Long id) {
        HttpSession session = request.getSession();
        session.setAttribute(CONST_ENTITY, id);
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
