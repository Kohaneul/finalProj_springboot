package com.deli.project.web.controller;

import com.deli.project.domain.entity.*;
import com.deli.project.domain.repository.BoardRepository;
import com.deli.project.domain.repository.OrderCheckRepository;
import com.deli.project.domain.service.*;
import com.deli.project.web.controller.form.BoardLikeForm;
import com.deli.project.web.controller.form.BoardSaveForm;
import com.deli.project.web.controller.form.CommentSaveForm;
import com.deli.project.web.controller.form.CommentUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String,Integer> boardMap = new HashMap<>();

    @GetMapping("/board/new/{orderId}")
    public String board(@PathVariable("orderId")Long orderId,HttpSession session, Model model){
        BoardSaveForm boardForm = setBoardForm(orderId, session);
        model.addAttribute("boardForm",boardForm);
        return "/board/BoardForm";
    }

    private BoardSaveForm setBoardForm(Long orderId, HttpSession session){
        Member member = memberService.findOne(SessionValue.getValue(session, USER_SESSION));
        PickUp pickUp = pickUpService.findOne(SessionValue.getValue(session, PICKUP_SESSION));
        Category category = categoryService.findOne(SessionValue.getValue(session, CATEGORY_SESSION));
        Restaurant restaurant = restaurantService.findOne(SessionValue.getValue(session, RESTAURANT_SESSION));
        BoardSaveForm boardForm = new BoardSaveForm(orderId,member.getLoginId(),member.getNickName(),pickUp.getPlaceName(),category.getCategoryName(),restaurant.getRestaurantName(),restaurant.getMinOrderPrice());
        return boardForm;
    }


    @PostMapping("/board/new/{orderId}")
    public String board2(@Valid @ModelAttribute("boardForm") BoardSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/board/BoardForm";
        }
        OrderCheck orderCheck = orderCheckRepository.findOne(form.getOrderId());
        Board board = Board.createBoard(orderCheck,form.getTitle(),form.getContent());
        boardRepository.save(board);
        log.info("저장 완료");
        Long boardId = board.getId();
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/board/{boardId}")
    public String boardCheck(@PathVariable Long boardId, Model model,HttpSession session){
        Board board = boardRepository.findOne(boardId);
        model.addAttribute("board",board);
        session.removeAttribute(CATEGORY_SESSION);
//        session.removeAttribute(RESTAURANT_SESSION);
        session.removeAttribute(MENU_SESSION);
        session.removeAttribute(ORDER_CHECK_SESSION);
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

    //글 상세 보기 ( +댓글까지)
    @GetMapping("/board/{boardId}/view")
    public String saveComment(@PathVariable Long boardId, Model model){
        Board board = boardRepository.findOne(boardId);
        model.addAttribute("board",board);
        model.addAttribute("commentForm",new CommentSaveForm());
        return "/board/BoardView";
    }

    //댓글 등록
    @PostMapping("/board/{boardId}/view")
    public String saveComment(@PathVariable Long boardId, @ModelAttribute("commentForm")CommentSaveForm commentForm, @SessionAttribute(LOGIN_ID)String loginId, RedirectAttributes redirectAttributes){
        Board board = boardRepository.findOne(boardId);
        Comment comment = new Comment(loginId,board,commentForm.getContent());
        boardRepository.saveComment(boardId,comment);
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board/{boardId}/view";
    }

    //댓글 수정하기
    @GetMapping("/board/comment/{boardId}/edit/{commentId}")
    public String updateComment(@PathVariable Long boardId,@PathVariable Long commentId,Model model){
        Board board = boardRepository.findOne(boardId);
        Comment comment = boardRepository.findBoardInComment(boardId, commentId);
        CommentUpdateForm commentForm = new CommentUpdateForm(commentId,comment.getContent());
        model.addAttribute("board",board);
        model.addAttribute("commentForm",commentForm);
        return "/board/comment/Board_Comment";
    }
    @PostMapping("/board/comment/{boardId}/edit/{commentId}")
    public String updateComment(@PathVariable Long boardId,@PathVariable Long commentId,@ModelAttribute("commentForm")CommentUpdateForm commentForm, RedirectAttributes redirectAttributes){
        boardRepository.updateComment(commentId,commentForm);
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board/{boardId}/view";
    }

    //댓글 지우기
    @GetMapping("/board/comment/{boardId}/delete/{commentId}")
    public String updateComment(@PathVariable Long boardId,@PathVariable Long commentId, RedirectAttributes redirectAttributes){
        boardRepository.deleteComment(commentId);
        redirectAttributes.addAttribute("boardId",boardId);
        return "redirect:/board/{boardId}/view";
    }

    //좋아요 카운트
    @GetMapping("/board/like/{boardId}")
    public String likeBoard(@PathVariable Long boardId,@SessionAttribute(name = LOGIN_ID)String loginId){
        BoardLikeForm boardLikeCount = new BoardLikeForm(boardId,loginId);
        boardRepository.likeCount(boardLikeCount,boardMap);
        return "redirect:/board/{boardId}/view";
    }


}