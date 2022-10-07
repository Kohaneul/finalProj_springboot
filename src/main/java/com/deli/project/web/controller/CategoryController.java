package com.deli.project.web.controller;

import com.deli.project.domain.entity.PickUp;
import com.deli.project.domain.service.CategoryService;
import com.deli.project.domain.service.PickUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/select")
public class CategoryController {
    private final PickUpService pickUpService;
    private final CategoryService categoryService;

    @GetMapping("/step_3/category")
    private String pickUpCategory(@RequestParam("pickupId")Long pickupId, Model model){
        PickUp pickUp = pickUpService.findOne(pickupId);
        model.addAttribute("pickUp",pickUp);
        return "/map/CategorySelect";
    }
    @GetMapping("/step_3")
    private String totalPickUp(@RequestParam("pickupId")Long pickupId, Model model){
        PickUp pickUp = pickUpService.findOne(pickupId);
        model.addAttribute("pickUp",pickUp);
        return "/map/CategorySelect";
    }
}
