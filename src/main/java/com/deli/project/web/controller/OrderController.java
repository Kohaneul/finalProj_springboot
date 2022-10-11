package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.service.BoardService;
import com.deli.project.domain.service.OrderService;
import com.deli.project.web.controller.form.BoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class OrderController {

    private final OrderService orderService;

    private final BoardService boardService;


    @PostMapping("/{boardId}")
    public String selectFin(@PathVariable("orderId")Long orderId,@Valid @ModelAttribute("boardForm")BoardForm boardForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "board/BoardForm";
        }
        Board board = Board.createBoard(boardForm.getOrder(), boardForm.getTitle(), boardForm.getContent());
        Long boardId = boardService.save(board);
        redirectAttributes.addAttribute("boardId",boardId);
        return "board/BoardDetail";
    }


}
