package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Board;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.OrderCheck;
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
    private final OrderCheckService orderService;
    private final BoardService boardService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final PickUpService pickUpService;

    @GetMapping("/new/{orderId}")
    public String board(@PathVariable("orderId")Long orderId,@SessionAttribute(name = ConstEntity.USER_SESSION)Long userId,
                        @SessionAttribute(name=ConstEntity.PICKUP_SESSION)Long pickUpId, @SessionAttribute(name=ConstEntity.CATEGORY_SESSION)Long categoryId
            ,Model model){
        OrderCheck order = orderService.findOne(orderId);
        BoardForm boardForm = new BoardForm(order,memberService.findOne(userId).getNickName(),pickUpService.findOne(pickUpId).getPlaceName(),
                categoryService.findOne(categoryId).getCategoryName(),order.getRestaurant().getRestaurantName(),order.getRestaurant().getMinOrderPrice());
        model.addAttribute("boardForm",boardForm);

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



    @GetMapping("/all")
    public String boardAll(Model model){
        List<Board> boards = boardService.findAll(new BoardSearchDto());
        model.addAttribute("boards",boards);
        return "/board/BoardAll";
    }

}
