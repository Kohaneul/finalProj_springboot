package com.deli.project.web.controller;
import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.OrderSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    //메뉴 확인 전
    @GetMapping("/select")
    public String orderCheck(@RequestParam Long menuId,HttpSession session, Model model){
        Menu menu = menuRepository.findOne(menuId);
        String placeName = pickUpService.findOne((Long) session.getAttribute(PICKUP_SESSION)).getPlaceName();
        String categoryName = categoryService.findOne((Long) session.getAttribute(CATEGORY_SESSION)).getCategoryName();
        Restaurant restaurant = restaurantService.findOne((Long) session.getAttribute(RESTAURANT_SESSION));
        String restaurantName = restaurant.getRestaurantName();
        String loginId = memberService.findOne((Long)session.getAttribute(USER_SESSION)).getLoginId();
        OrderSaveForm saveForm = new OrderSaveForm(placeName,categoryName,restaurantName,restaurant.getAddress().getCity() +" "+ restaurant.getAddress().getState(),menuId,loginId);
        model.addAttribute("saveForm",saveForm);
        model.addAttribute("menu",menu);
        return "/order/OrderCheck";
    }
    @PostMapping("/select")
    public String orderCheck(@ModelAttribute(name = "saveForm") OrderSaveForm saveForm,HttpSession session, RedirectAttributes redirectAttributes){
        Menu menu = menuRepository.findOne(saveForm.getMenuId());
        OrderCheck orderCheck = OrderCheck.createOrder(saveForm.getRestaurantName(), saveForm.getLoginId(),menu);
        orderCheckRepository.save(orderCheck);
        Long orderId = orderCheck.getId();
        log.info("orderId={}",orderId);
        session.setAttribute(ORDER_CHECK_SESSION,orderId);
        redirectAttributes.addAttribute("orderId",orderId);
        return "redirect:/board/new/{orderId}";
    }


}
