package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.RestaurantDto;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.OrderForm;
import com.deli.project.web.controller.form.RestaurantSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.deli.project.domain.ConstEntity.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/select")
public class RestaurantController {
    private final MemberService memberService;
    private final OrderService orderService;
    private final RestaurantService restaurantService;
    private final CategoryService categoryService;
    private final PickUpService pickUpService;
    @GetMapping("/restaurant")
    private String totalPickUp(@RequestParam("categoryId")Long categoryId, @SessionAttribute(PICKUP_SESSION)Long pickupId, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.setAttribute(ConstEntity.CATEGORY_SESSION,categoryId);
        PickUp pickUp = pickUpService.findOne(pickupId);
        String pickUpAddress = pickUp.getAddress();
        Category category = categoryService.findOne(categoryId);
        model.addAttribute("category",category);
        model.addAttribute("pickUp",pickUp);
        List<Restaurant> restaurants = restaurantService.findDto(new RestaurantDto(categoryId, pickUpAddress));
        model.addAttribute("restaurants",restaurants);
        return "/restaurant/RestaurantSelect";
    }

    @GetMapping("/{restaurantId}")
    public String restaurantSelect2(@PathVariable("restaurantId") Long restaurantId,
                                    @SessionAttribute(PICKUP_SESSION)Long pickupId,
                                    @SessionAttribute(CATEGORY_SESSION)Long categoryId,
                                    Model model){

        //픽업장소,카테고리,장소명,주소
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        RestaurantSaveForm form = new RestaurantSaveForm(pickUpService.findOne(pickupId),categoryService.findOne(categoryId), restaurant,restaurant.getAddress());
        model.addAttribute("saveForm",form);
        log.info("restaurantID={}",restaurantId);
        model.addAttribute("restaurant",restaurantService.findOne(restaurantId));

        return "/order/OrderCheck";
    }

    @PostMapping("/{restaurantId}")
    public String restaurantSelect3(@PathVariable("restaurantId") Long restaurantId, @SessionAttribute(USER_SESSION)Long userId,@SessionAttribute(PICKUP_SESSION)Long pickupId, RedirectAttributes redirectAttributes){
        String loginId = memberService.findOne(userId).getLoginId();
        Order order = Order.createOrder(restaurantService.findOne(restaurantId),loginId);
        Long orderId = orderService.saveOrder(order);
        redirectAttributes.addAttribute("orderId",orderId);
        return "redirect:/board/new/{orderId}";

    }


}
