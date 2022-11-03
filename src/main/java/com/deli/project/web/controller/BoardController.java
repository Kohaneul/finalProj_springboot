package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.BoardRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.BoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
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
public class BoardController {

    private final OrderCheckRepository orderCheckRepository;
    private final BoardRepository boardRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final RestaurantService restaurantService;
    private final PickUpService pickUpService;
    private String loginId;

    @GetMapping("/board/new/{orderId}")
    public String board(@PathVariable("orderId")Long orderId,HttpSession session, Model model){
        BoardForm boardForm = setBoardForm(session);
        model.addAttribute("boardForm",boardForm);
        return "/board/BoardForm";
    }

    private BoardForm setBoardForm(HttpSession session){
        OrderCheck orderCheck = orderCheckRepository.findOne(SessionValue.getValue(session, ORDER_CHECK_SESSION));
        loginId = orderCheck.getLoginId();
        Member member = memberService.findOne(SessionValue.getValue(session, USER_SESSION));
        PickUp pickUp = pickUpService.findOne(SessionValue.getValue(session, PICKUP_SESSION));
        Category category = categoryService.findOne(SessionValue.getValue(session, CATEGORY_SESSION));
        Restaurant restaurant = restaurantService.findOne(SessionValue.getValue(session, RESTAURANT_SESSION));
        BoardForm boardForm = new BoardForm(orderCheck,member.getNickName(),pickUp.getPlaceName(),category.getCategoryName(),restaurant.getRestaurantName(),null,null,restaurant.getMinOrderPrice(),null);
        return boardForm;
    }


    @PostMapping("/board/new/{orderId}")
    public String board2(@Valid @ModelAttribute("boardForm")BoardForm form, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/board/BoardForm";
        }
        Board board = Board.createBoard(form.getOrder(),form.getTitle(),form.getContent());
        boardRepository.save(board);
        Long boardId = board.getId();
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/board/{boardId}")
    public String boardCheck(@PathVariable Long boardId, Model model){
        Board board = boardRepository.findOne(boardId);
        model.addAttribute("board",board);
        return "/board/BoardDetail";
    }

    @GetMapping("/boards")
    public String boardAll(@ModelAttribute("boardSearch")BoardSearchDto boardSearch, Model model){
        List<Board> boards = boardRepository.findAll(boardSearch);
        boardTextedit(boards);
        model.addAttribute("boards",boards);
        return "/board/BoardAll";
    }

    private void boardTextedit(List<Board> boards) {
        for (Board board : boards) {
            StringBuilder sb = new StringBuilder();
            String str = sb.append(board.getContent()).toString();
            if(str.length()>20){
                board.setContent(sb.delete(20,str.length()).append("....").toString());
            }
            else{
                board.setContent(sb.append("...").toString());
            }
        }
    }


    @GetMapping("/board/{boardId}/view")
    public String boardView(@PathVariable Long boardId, Model model){
        Board board = boardRepository.findOne(boardId);
        model.addAttribute("board",board);
        return "/board/BoardView";
    }

    @PostMapping("/board/{boardId}/view")
    public String boardView(@PathVariable Long boardId,@RequestParam("comment") String comment, Model model){
        Board board = boardRepository.findOne(boardId);
        board.getComments().add(new Comment(loginId,comment,0));
        model.addAttribute("board",board);
        return "/board/BoardView";

    }


}