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
import static com.deli.project.domain.ConstEntity.*;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class OrderCheckController {
    private final MenuRepository menuRepository;
    private final MemberService memberService;
    private final OrderCheckRepository orderCheckRepository;
    private final PickUpService pickUpService;


    @GetMapping("/select")
    public String orderCheck(@RequestParam Long menuId,@SessionAttribute(name = PICKUP_SESSION)Long pickUpId, Model model){
        Menu menu = menuRepository.findOne(menuId);
        PickUp pickUp = pickUpService.findOne(pickUpId);
        log.info("pickUP={}",pickUp.getPlaceName());
        OrderSaveForm saveForm = new OrderSaveForm(menu.getRestaurant(),pickUp,menu);
        model.addAttribute("saveForm",saveForm);
        return "/order/OrderCheck";
    }
    @PostMapping("/select")
    public String orderCheck(@ModelAttribute(name = "saveForm") OrderSaveForm saveForm, HttpSession session,RedirectAttributes redirectAttributes){
        Menu menu = menuRepository.findOne((Long) session.getAttribute(MENU_SESSION));

        OrderCheck orderCheck = OrderCheck.createOrder(saveForm.getRestaurant(),memberService.findOne((Long) session.getAttribute(USER_SESSION)).getLoginId(),saveForm.getMenu());
        orderCheckRepository.save(orderCheck);
        log.info("orderId={}",orderCheck.getId());
        session.setAttribute(ORDER_CHECK_SESSION,orderCheck.getId());
        redirectAttributes.addAttribute("orderId",orderCheck.getId());
        return "redirect:/board/new/{orderId}";
    }


}
