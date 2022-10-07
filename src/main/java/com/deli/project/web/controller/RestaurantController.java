package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.Category;
import com.deli.project.domain.entity.PickUp;
import com.deli.project.domain.entity.Restaurant;
import com.deli.project.domain.repository.RestaurantDto;
import com.deli.project.domain.service.CategoryService;
import com.deli.project.domain.service.PickUpService;
import com.deli.project.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.deli.project.domain.ConstEntity.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/select")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final CategoryService categoryService;
    private final PickUpService pickUpService;
    @GetMapping("/step_4")
    private String totalPickUp(@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        session.setAttribute(ConstEntity.CATEGORY_SESSION,categoryId);
        log.info("categoryId={}",categoryId);
        PickUp pickUp = pickUpService.findOne(pickupId);
        String pickUpAddress = pickUp.getAddress();
        Category category = categoryService.findOne(categoryId);
        model.addAttribute("category",category);
        model.addAttribute("pickUp",pickUp);
        log.info("pickUp={}",pickUp.getAddress());
        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDto(categoryId, pickUpAddress));
//        Restaurant restaurant = Restaurant.setRestaurant(pickUp.getPlaceName(),new Address(pickUpAddress.split(" ")[0],pickUpAddress.split(" ")[1]),category);
//        restaurants.add(restaurant);
        log.info("restaurants={}",restaurants.size());
        model.addAttribute("restaurants",restaurants);
        return "/restaurant/RestaurantSelect";
    }

    @GetMapping
    public String restaurantSelect2(@RequestParam("restaurantId")Long restaurantId, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(RESTAURANT_SESSION,restaurantId);
        return "order/Order";
    }


}
