package com.deli.project.web.controller;

import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.MenuRepository;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.BoardForm;
import com.deli.project.web.controller.form.OrderSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.deli.project.domain.ConstEntity.PICKUP_SESSION;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class OrderCheckController {
    private final MenuRepository menuRepository;

    private final OrderCheckService orderService;
    private final MemberService memberService;
    private final BoardService boardService;

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
    public String orderCheck(@Valid @ModelAttribute("saveForm")OrderSaveForm orderSaveForm,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.info("rejectedValue={}",fieldError.getRejectedValue());
                log.info("message={}",fieldError.getDefaultMessage());
            }
            return "/order/OrderCheck";
        }
        OrderCheck orderCheck = OrderCheck.createOrder(orderSaveForm.getRestaurant(),orderSaveForm.getPickUp().getMember().getLoginId(),orderSaveForm.getMenu());
        Long orderId = orderService.saveOrder(orderCheck);

        log.info("orderId={}",orderId);
        redirectAttributes.addAttribute("orderId",orderId);
        return "redirect:/board/new/{orderId}";
    }



//
//
//
//    @GetMapping("/check/{orderCheckId}")
//    public String boardCheck(@PathVariable Long orderCheckId, Model model){
//        OrderCheck orderCheck = orderService.findOne(orderCheckId);
//        Restaurant restaurant=orderCheck.getRestaurant();
//        String nickName = memberService.findLoginId(orderCheck.getLoginId()).getNickName();
//        BoardForm boardForm = new BoardForm(orderCheck, nickName,restaurant.getPickUp().getPlaceName(), restaurant.getCategory().getCategoryName(), restaurant.getRestaurantName(), restaurant.getMinOrderPrice());
//        model.addAttribute("boardForm",boardForm);
//        return "/board/BoardForm";
//    }
//    @PostMapping("/check/{orderCheckId}")
//    public String selectFin(@Valid @ModelAttribute("boardForm")BoardForm boardForm,BindingResult bindingResult,RedirectAttributes redirectAttributes){
//        if(bindingResult.hasErrors()){
//            return "board/BoardForm";
//        }
//        Board board = Board.createBoard(boardForm.getOrder(), boardForm.getTitle(), boardForm.getContent());
//        Long boardId = boardService.save(board);
//        redirectAttributes.addAttribute("boardId",boardId);
//        return "redirect:/board/{boardId}";
//    }
//
//    @GetMapping("/{boardId}")
//    public String boardDetail(@PathVariable("boardId")Long boardId,Model model){
//        Board board = boardService.findOne(boardId);
//        model.addAttribute("board",board);
//        return "/board/BoardDetail";
//    }
//
//


}
