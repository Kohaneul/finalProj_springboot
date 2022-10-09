package com.deli.project.web.controller;

import com.deli.project.domain.entity.Delivery;
import com.deli.project.domain.entity.DeliveryStatus;
import com.deli.project.domain.entity.Order;
import com.deli.project.domain.entity.Restaurant;
import com.deli.project.domain.repository.OrderRepository;
import com.deli.project.domain.service.MemberService;
import com.deli.project.domain.service.OrderService;
import com.deli.project.domain.service.RestaurantService;
import com.deli.project.web.controller.form.RestaurantSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

import static com.deli.project.domain.ConstEntity.*;
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/order")
public class OrderController {

    private final OrderService orderService;
    private final RestaurantService restaurantService;




    @GetMapping("/{id}")
    public String orderCheckFin(@PathVariable Long id){
        Restaurant restaurant = restaurantService.findOne(id);

        String address = restaurant.getAddress().getCity() + restaurant.getAddress().getState();
        String placeName = restaurant.getPickUp().getPlaceName();
        String loginId = restaurant.getPickUp().getMember().getLoginId();

        Order order = Order.createOrder(restaurant, LocalDateTime.now(),new Delivery(DeliveryStatus.ING));
        orderService.saveOrder(order);
        return "board/BoardForm";
    }


}
