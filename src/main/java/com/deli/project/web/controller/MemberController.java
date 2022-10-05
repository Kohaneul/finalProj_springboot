package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.MemberSort;
import com.deli.project.domain.repository.MemberSearch;
import com.deli.project.domain.service.DuplicateService;
import com.deli.project.domain.service.MemberService;
import com.deli.project.web.controller.form.LoginMember;
import com.deli.project.web.controller.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final DuplicateService duplicateService;

    @GetMapping("/new")
    public String join(@ModelAttribute("memberForm") MemberForm memberForm){
        return "member/Member";
    }

    private void duplicatedCheck(String loginId,String nickName,BindingResult bindingResult){
        if(duplicateService.duplicatedLoginId(loginId)>0){
            bindingResult.reject("globalError","현재 사용중인 아이디 입니다.");
        }
        if(duplicateService.duplicatedNickName(nickName)>0){
            bindingResult.reject("globalError","현재 사용중인 닉네임 입니다.");
        }
    }



    @PostMapping("/new")
    public String join(@Valid @ModelAttribute("memberForm")MemberForm memberForm, BindingResult bindingResult, HttpServletResponse response, RedirectAttributes redirectAttributes,Model model) throws IOException {
        duplicatedCheck(memberForm.getLoginId(),memberForm.getNickName(),bindingResult);
        pwEqualCheck(memberForm.getPassword(), memberForm.getPasswordCheck(),bindingResult);
        if(bindingResult.hasErrors()){
            return "member/Member";
        }
        Member member = Member.createMember(memberForm.getLoginId(),memberForm.getPassword(), memberForm.getNickName(), memberForm.getPhoneNumber(),
                memberForm.getMemberSort(),new Address(memberForm.getCity(),memberForm.getCity(),memberForm.getZipCode()));
        Long memberId = memberService.saveMember(member);
        Member member1 = memberService.findOne(memberId);
        model.addAttribute("member",member1);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        redirectAttributes.addAttribute("id",memberId);
        model.addAttribute("member",member);
        out.println("<script>alert('멤버 입력 확인 창으로 이동합니다.'); location.href='/member/{id}';</script>");
        out.flush();
        return "redirect:/member/{id}";
    }
    @GetMapping("/{id}")
    public String member(@PathVariable Long id,Model model){
        Member member = memberService.findOne(id);
        model.addAttribute("member",member);
        return "member/MemberCheck";
    }

    @GetMapping("/all")
    public String memberAll(@ModelAttribute("memberSearch") MemberSearch memberSearch, Model model){
        List<Member> members = memberService.findAll(memberSearch);
        model.addAttribute("members",members);
        return "member/MemberAll";
    }
    @GetMapping("/login")
    public String login(@ModelAttribute("loginMember")LoginMember loginMember){
        return "member/Login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginMember")LoginMember loginMember, BindingResult bindingResult,HttpServletRequest request){
        Long id = memberService.loginMember(loginMember.getLoginId(), loginMember.getPassword());
        if(id==null){
            bindingResult.reject("globalError","아이디/비밀번호 입력 오류");
        }
        if(bindingResult.hasErrors()){
            return "member/Login";
        }
        setLoginSession(request, id);
        return "redirect:/";
    }

    private void setLoginSession(HttpServletRequest request, Long id) {
        HttpSession session = request.getSession();
        String uuid = UUID.randomUUID().toString();
        if(memberService.findOne(id).getMemberSort().equals(MemberSort.ADMIN)){
            uuid ="admin"+uuid;
        }
        session.setAttribute(ConstEntity.SESSION,uuid);
    }


    private void pwEqualCheck(String password, String passwordCheck, BindingResult bindingResult) {
        log.info("password",password);
        log.info("passwordCheck",passwordCheck);
        if(!password.equals(passwordCheck)){
            bindingResult.reject("globalError","비밀번호를 다시 입력해주세요");
        }
    }


}
