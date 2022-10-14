package com.deli.project.web.controller;

import com.deli.project.domain.entity.*;
import com.deli.project.domain.service.BoardService;
import com.deli.project.domain.service.MemberService;
import com.deli.project.domain.service.OrderCheckService;
import com.deli.project.domain.service.RestaurantService;
import com.deli.project.web.controller.form.BoardForm;
import com.deli.project.web.controller.form.OrderSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class OrderCheckController {

    private final OrderCheckService orderService;
    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("/check/{orderCheckId}")
    public String boardCheck(@PathVariable Long orderCheckId, Model model){
        OrderCheck orderCheck = orderService.findOne(orderCheckId);
        Restaurant restaurant=orderCheck.getRestaurant();
        String nickName = memberService.findLoginId(orderCheck.getLoginId()).getNickName();
        BoardForm boardForm = new BoardForm(orderCheck, nickName,restaurant.getPickUp().getPlaceName(), restaurant.getCategory().getCategoryName(), restaurant.getRestaurantName(), restaurant.getMinOrderPrice());
        model.addAttribute("boardForm",boardForm);
        return "/board/BoardForm";
    }
    @PostMapping("/check/{orderCheckId}")
    public String selectFin(@Valid @ModelAttribute("boardForm")BoardForm boardForm,BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "board/BoardForm";
        }
        Board board = Board.createBoard(boardForm.getOrder(), boardForm.getTitle(), boardForm.getContent());
        Long boardId = boardService.save(board);
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/{boardId}")
    public String boardDetail(@PathVariable("boardId")Long boardId,Model model){
        Board board = boardService.findOne(boardId);
        model.addAttribute("board",board);
        return "/board/BoardDetail";
    }




}
