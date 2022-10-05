package com.deli.project.web.controller.form;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.MemberSort;
import com.deli.project.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LoginController {


    private final MemberService memberService;


    @GetMapping("/login")
    public String login(@ModelAttribute("loginMember")LoginMember loginMember){
        return "member/Login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginMember")LoginMember loginMember, BindingResult bindingResult, HttpServletRequest request){
        Member member = memberService.loginMember(loginMember.getLoginId(), loginMember.getPassword());
        if(member==null){
            bindingResult.reject("globalError","아이디/비밀번호 입력 오류");
        }
        if(bindingResult.hasErrors()){
            return "member/Login";
        }
        setLoginSession(request, member);
        return "redirect:/";
    }

    private void setLoginSession(HttpServletRequest request,Member member) {
        HttpSession session = request.getSession();
        String uuid = UUID.randomUUID().toString();
        if(member.getMemberSort().equals(MemberSort.ADMIN)){
            uuid ="admin"+uuid;
        }
        session.setAttribute(ConstEntity.SESSION,uuid);
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
