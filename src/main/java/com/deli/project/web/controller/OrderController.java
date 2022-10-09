package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Delivery;
import com.deli.project.domain.entity.DeliveryStatus;
import com.deli.project.domain.entity.Order;
import com.deli.project.domain.entity.Restaurant;
import com.deli.project.domain.service.OrderService;
import com.deli.project.domain.service.RestaurantService;
import com.deli.project.web.controller.form.BoardForm;
import com.deli.project.web.controller.form.RestaurantSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.deli.project.domain.ConstEntity.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/order")
public class OrderController {

    private final OrderService orderService;
    private final RestaurantService restaurantService;


    @GetMapping("/{orderId}")
    public String orderCheckFin(@PathVariable("orderId") Long orderId,@ModelAttribute BoardForm boardForm){
        Order order = orderService.findOne(orderId);
        boardForm.setOrder(order);
        return "board/BoardForm";
    }

    @PostMapping
    public String selectFin(@ModelAttribute("saveForm")RestaurantSaveForm saveForm, HttpServletRequest request){
        Restaurant restaurant = restaurantService.findOne(saveForm.getRestaurant().getId());
        Order order = Order.createOrder(restaurant,saveForm.getPickUp().getMember().getLoginId(),new Delivery(DeliveryStatus.ING));
        Long orderId = orderService.saveOrder(order);
        HttpSession session = request.getSession();
        session.setAttribute(ConstEntity.ORDER_SESSION,orderId);
        return "board/BoardDetail";
    }

}
