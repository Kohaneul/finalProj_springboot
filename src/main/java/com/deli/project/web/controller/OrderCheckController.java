package com.deli.project.web.controller;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.OrderSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.deli.project.domain.ConstEntity.*;

/**
 * 최종 주문 확인용 컨트롤러
 */

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class OrderCheckController {
    private final MenuRepository menuRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final OrderCheckRepository orderCheckRepository;
    private final RestaurantService restaurantService;
    private final PickUpService pickUpService;
    @GetMapping("/select")
    public String orderCheck(@RequestParam("menuId") String menuId,HttpSession session,Model model){
        List<Long> menuIds = Arrays.stream(menuId.split("[,]")).map(i -> Long.valueOf(i)).collect(Collectors.toList());
        Restaurant restaurant = restaurantService.findOne(SessionValue.getValue(session, RESTAURANT_SESSION));
        String restaurantName = restaurant.getRestaurantName();
        String address = restaurant.getAddress().getCity() + " " + restaurant.getAddress().getState();
        String categoryName = categoryService.findOne(SessionValue.getValue(session, CATEGORY_SESSION)).getCategoryName();
        PickUp pickUp = pickUpService.findOne(SessionValue.getValue(session, PICKUP_SESSION));
        String pickUpPlace=pickUp.getPlaceName();
        Member member = memberService.findOne(SessionValue.getValue(session, USER_SESSION));
        String LoginId = member.getLoginId();
        List<Menu> menus = menuRepository.chooseMenus(menuIds);
        int totalPrice = menus.stream().mapToInt(Menu::getPrice).sum();
        OrderSaveForm saveForm = new OrderSaveForm(pickUpPlace, categoryName, restaurantName, address, menuIds, LoginId,totalPrice);
        model.addAttribute("saveForm",saveForm);
        model.addAttribute("menus",menus);
        return "/order/OrderCheck";
    }
    @PostMapping("/select")
    public String orderCheck(@ModelAttribute(name = "saveForm") OrderSaveForm saveForm,HttpSession session, RedirectAttributes redirectAttributes){
        List<Menu> menus = menuRepository.chooseMenus(saveForm.getChooseMenuIds());
        OrderCheck orderCheck = OrderCheck.createOrder(saveForm.getRestaurantName(), saveForm.getLoginId(),menus);
        System.out.println("*************orderCheck = " + orderCheck.getRestaurantName());
        orderCheckRepository.save(orderCheck);
        log.info("orderId={}",orderCheck.getId());
        Long orderId = orderCheck.getId();
        session.setAttribute(ORDER_CHECK_SESSION,orderId);
        redirectAttributes.addAttribute("orderId",orderId);
        return "redirect:/board/new/{orderId}";
    }


}