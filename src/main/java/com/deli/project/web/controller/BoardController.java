package com.deli.project.web.controller;

import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.BoardRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.BoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static com.deli.project.domain.ConstEntity.*;
/**
 * 게시글 작성
 *
 * */
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final OrderCheckRepository orderCheckRepository;
    private final BoardRepository boardRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final RestaurantService restaurantService;
    private final PickUpService pickUpService;

    @GetMapping("/new/{orderId}")
    public String board(@PathVariable("orderId")Long orderId,HttpSession session, Model model){
        BoardForm boardForm = setBoardForm(session);
        model.addAttribute("boardForm",boardForm);
        return "/board/BoardForm";
    }

    private BoardForm setBoardForm(HttpSession session){
        OrderCheck orderCheck = orderCheckRepository.findOne((Long) session.getAttribute(ORDER_CHECK_SESSION));
        Member member = memberService.findOne((Long) session.getAttribute(USER_SESSION));
        PickUp pickUp = pickUpService.findOne((Long) session.getAttribute(PICKUP_SESSION));
        Category category = categoryService.findOne((Long) session.getAttribute(CATEGORY_SESSION));
        Restaurant restaurant = restaurantService.findOne((Long) session.getAttribute(RESTAURANT_SESSION));
        BoardForm boardForm = new BoardForm(orderCheck,member.getNickName(),pickUp.getPlaceName(),category.getCategoryName(),restaurant.getRestaurantName(),null,null,restaurant.getMinOrderPrice());

        return boardForm;
    }


    @PostMapping("/new/{orderId}")
    public String board2(@Valid @ModelAttribute("boardForm")BoardForm form, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/board/BoardForm";
        }
        Board board = Board.createBoard(form.getOrder(),form.getTitle(),form.getContent());
        boardRepository.save(board);
        redirectAttributes.addAttribute("boardId",board.getId());
        return "redirect:/board/{boardId}";
    }



    @GetMapping("/all")
    public String boardAll(Model model){
        List<Board> boards = boardRepository.findAll(new BoardSearchDto());
        model.addAttribute("boards",boards);
        return "/board/BoardAll";
    }

}
