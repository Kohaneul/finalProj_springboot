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
import com.deli.project.web.controller.form.RestaurantSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private String totalPickUp(@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.setAttribute(ConstEntity.CATEGORY_SESSION,categoryId);
        log.info("categoryId={}",categoryId);
        PickUp pickUp = pickUpService.findOne(pickupId);
        String pickUpAddress = pickUp.getAddress();
        Category category = categoryService.findOne(categoryId);
        model.addAttribute("category",category);
        model.addAttribute("pickUp",pickUp);
        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDto(categoryId, pickUpAddress));
        model.addAttribute("restaurants",restaurants);
        return "/restaurant/RestaurantSelect";
    }
    @GetMapping("/step_5")
    public String restaurantSelect2(@RequestParam("restaurantId")Long restaurantId,
                                    @SessionAttribute(PICKUP_SESSION)Long pickupId,
                                    @SessionAttribute(CATEGORY_SESSION)Long categoryId,
                                    @SessionAttribute(USER_SESSION)Long memberId,
                                    Model model){

        //픽업장소,카테고리,장소명,주소

        Restaurant restaurant = restaurantService.findOne(restaurantId);

        RestaurantSaveForm form = new RestaurantSaveForm(pickUpService.findOne(pickupId),categoryService.findOne(categoryId), restaurant,restaurant.getAddress());
        model.addAttribute("saveForm",form);
        model.addAttribute("restaurant",restaurantService.findOne(restaurantId));
        return "/order/OrderCheck";
    }



//    @GetMapping("/{id}")
//    public String restaurantCheck(@PathVariable("restaurantId")Long restaurantId,@SessionAttribute(PICKUP_SESSION)Long pickupId,@SessionAttribute(CATEGORY_SESSION)Long categoryId,
//                                  @SessionAttribute(USER_SESSION)Long memberId,HttpServletRequest request,Model model){
//        RestaurantSaveForm form = new RestaurantSaveForm(pickupId,categoryId,memberId,restaurantId);
//        model.addAttribute("restaurantForm",form);
//
//        return "/order/OrderCheck";
//    }
//
//


}
