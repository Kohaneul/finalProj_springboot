package com.deli.project.web.controller;

import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.repository.MemberSearch;
import com.deli.project.domain.service.MemberService;
import com.deli.project.web.controller.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/new")
    public String join(@ModelAttribute("memberForm") MemberForm memberForm){
        return "member/Member";
    }



    @GetMapping("/idCheck")
    public String join(@RequestParam String loginId,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException {
        duplicateLoginId(loginId,response);

        redirectAttributes.addAttribute("loginId",loginId);
        log.info("llllllllllllloginId={}",loginId);
        return "redirect:/member/{loginId}";
    }

    @GetMapping("/{loginId}")
    public String loginIdSet(@RequestParam String loginId, Model model){
        MemberForm memberForm = new MemberForm();
        memberForm.setLoginId(loginId);
        model.addAttribute("memberForm",memberForm);
        return "redirect:/member/new";

    }

//
//    @GetMapping("/{loginId}")
//    public String login(@ModelAttribute("memberForm")MemberForm memberForm,Model model){
//
//        return "member/Member";
//    }

//

    private void duplicateLoginId(String loginId,HttpServletResponse response) throws IOException {
        log.info("memberService = {}",memberService.findLoginId(loginId)==null);
        if(memberService.findLoginId(loginId).size()==1){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 중복!'); location.href='/member/new';</script>");
            out.flush();
        }
    }


    @PostMapping("/new")
    public String join(@Valid @ModelAttribute("memberForm")MemberForm memberForm, BindingResult bindingResult, HttpServletResponse response, RedirectAttributes redirectAttributes,Model model) throws IOException {
        duplicateNickNameCheck(memberForm.getNickName(),bindingResult);
        log.info("memberForm password={}",memberForm.getPassword());
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




    private void duplicateNickNameCheck(String nickName,BindingResult bindingResult){
        if(memberService.findNickName(nickName)!=null){
            bindingResult.reject("globalError","기존 아이디가 존재합니다.");
        }
    }

    private void pwEqualCheck(String password, String passwordCheck, BindingResult bindingResult) {
        if(!password.equals(passwordCheck)){
            bindingResult.reject("globalError","비밀번호를 다시 입력해주세요");
        }
    }


}
