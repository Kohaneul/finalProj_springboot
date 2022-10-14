package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.repository.RestaurantDto;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.OrderSaveForm;
import com.deli.project.web.controller.form.RestaurantSaveForm;
import com.querydsl.core.types.Order;
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

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/select")
public class RestaurantController {
    private final MemberService memberService;
    private final OrderCheckService orderService;
    private final RestaurantService restaurantService;
    private final CategoryService categoryService;
    private final PickUpService pickUpService;
    private final MenuRepository menuRepository;

//    @GetMapping("/restaurant")
//    public String resChoose(@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId, HttpServletRequest request, Model model){
//        HttpSession session = request.getSession();
//        session.setAttribute(ConstEntity.CATEGORY_SESSION,categoryId);
//        PickUp pickUp = pickUpService.findOne(pickupId);
//        String pickUpAddress = pickUp.getAddress();
//        Category category = categoryService.findOne(categoryId);
//        model.addAttribute("category",category);
//        model.addAttribute("pickUp",pickUp);
//        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDto(categoryId, pickUpAddress));
//        model.addAttribute("restaurants",restaurants);
//        return "/restaurant/RestaurantSelect";
//    }

    @GetMapping("/restaurant")
    public String resChoose(@ModelAttribute("saveForm")RestaurantSaveForm saveForm,@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.setAttribute(ConstEntity.CATEGORY_SESSION,categoryId);
        saveForm.setCategory(categoryService.findOne(categoryId));
        PickUp pickUp = pickUpService.findOne(pickupId);
        saveForm.setPickUp(pickUp);
        saveForm.setAddress(new Address(pickUp.getAddress().split(" ")[0],pickUp.getAddress().split(" ")[1]));
        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDto(categoryId, pickUp.getAddress()));
        model.addAttribute("restaurants",restaurants);
        model.addAttribute("pickUp",pickUp);
        return "/restaurant/RestaurantSelect";
    }


    @PostMapping("/restaurant")
    public String resChoose2(@RequestParam("restaurantId")Long restaurantId,@Valid @ModelAttribute("saveForm")RestaurantSaveForm saveForm, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
           return "/restaurant/RestaurantSelect";
        }
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

    @GetMapping
    public String orderCheck(@RequestParam Long menuId,@SessionAttribute(name = PICKUP_SESSION)Long pickUpId, Model model){
        Menu menu = menuRepository.findOne(menuId);
        PickUp pickUp = pickUpService.findOne(pickUpId);
        OrderSaveForm saveForm = new OrderSaveForm(menu.getRestaurant(),pickUp,menu);

        model.addAttribute("saveForm",saveForm);
        return "/order/OrderCheck";
    }
    @PostMapping
    public String orderCheck(@ModelAttribute("saveForm")OrderSaveForm orderSaveForm,RedirectAttributes redirectAttributes){
        OrderCheck orderCheck = OrderCheck.createOrder(orderSaveForm.getRestaurant(),orderSaveForm.getPickUp().getMember().getLoginId(),orderSaveForm.getMenu());
        Long orderId = orderService.saveOrder(orderCheck);
        redirectAttributes.addAttribute("orderId",orderId);
        return "redirect:/board/new/{orderId}";
    }


}
