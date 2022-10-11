package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Board;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.Order;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.BoardForm;
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
public class BoardController {
    private final OrderService orderService;
    private final BoardService boardService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final PickUpService pickUpService;

    @GetMapping("/new/{orderId}")
    public String board(@PathVariable("orderId")Long orderId,@ModelAttribute("boardForm") BoardForm boardForm,
                        @SessionAttribute(ConstEntity.PICKUP_SESSION)Long pickUpId, @SessionAttribute(ConstEntity.CATEGORY_SESSION)Long categoryId){
        Order order = orderService.findOne(orderId);
        Member member= memberService.findLoginId(order.getLoginId());
        boardForm.setOrder(order);
        boardForm.setCategory(categoryService.findOne(categoryId).getCategoryName());
        boardForm.setNickName(member.getNickName());
        boardForm.setPickUpName(pickUpService.findOne(pickUpId).getPlaceName());
        boardForm.setRestaurantName(order.getRestaurant().getRestaurantName());
        return "/board/BoardForm";
    }

    @PostMapping("/new/{orderId}")
    public String board2(@Valid @ModelAttribute("boardForm")BoardForm form, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/board/BoardForm";
        }
        Board board = Board.createBoard(form.getOrder(),form.getTitle(),form.getContent());
        Long boardId = boardService.save(board);
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/{boardId}")
    public String boardCheck(@PathVariable Long boardId, Model model){
        Board board = boardService.findOne(boardId);
        model.addAttribute("board",board);
        Order order = orderService.findOne(boardId);
        model.addAttribute("order",order);
        return "/board/BoardDetail";
    }


    @GetMapping("/all")
    public String boardAll(Model model){
        List<Board> boards = boardService.findAll(new BoardSearchDto());
        model.addAttribute("boards",boards);
        return "/board/BoardAll";
    }

}
