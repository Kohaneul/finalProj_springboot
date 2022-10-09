package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Board;
import com.deli.project.domain.entity.Order;
import com.deli.project.domain.service.BoardService;
import com.deli.project.domain.service.OrderService;
import com.deli.project.web.controller.form.BoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final OrderService orderService;
    private final BoardService boardService;
    @GetMapping("/new")
    public String board(@SessionAttribute(name= ConstEntity.ORDER_SESSION) Long orderId, @ModelAttribute("boardForm") BoardForm boardForm){
        Order order = orderService.findOne(orderId);
        boardForm.setOrder(order);
        return "/board/BoardForm";
    }

    @PostMapping("/new")
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

        //글번호, 작성자, 주문번호, 제목, 내용



        return "/board/BoardDetail";
    }

}
