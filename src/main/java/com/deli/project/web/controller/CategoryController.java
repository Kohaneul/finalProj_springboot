package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Category;
import com.deli.project.domain.service.CategoryService;
import com.deli.project.domain.service.PickUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/select")
public class CategoryController {
    private final PickUpService pickUpService;
    private final CategoryService categoryService;

    @GetMapping("/category")
    private String pickUpCategory(@RequestParam("pickupId")Long pickupId, Model model, HttpSession session){
        session.setAttribute(ConstEntity.PICKUP_SESSION,pickupId);  //선택한 픽업 pk값 세션에 저장
        List<Category> categories = categoryService.findAll();
        model.addAttribute("placeName",pickUpService.findOne(pickupId).getPlaceName());
        model.addAttribute("categories",categories);
        return "/pickup/CategorySelect";
    }



}
