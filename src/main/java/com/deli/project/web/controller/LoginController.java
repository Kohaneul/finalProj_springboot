package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.MemberSort;
import com.deli.project.domain.service.MemberService;
import com.deli.project.web.controller.form.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

/**로그인 컨트롤러
 * 1. 로그인시 사용자 계정을 임의의 uuid로 생성하여 세션 저장
 * 2. uuid를 통하여 admin, 일반 계정 분류하여 특정 url 조회 가능
 *
 * */

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String login(@ModelAttribute("loginMember") LoginMember loginMember, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute(ConstEntity.SESSION)!=null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그아웃 되셨습니다.'); location.href='/logout';</script>");
            out.flush();
        }
        return "/member/Login";
    }


    // 로그인 실행
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginMember")LoginMember loginMember, BindingResult bindingResult, HttpServletRequest request){
        Member member = memberService.loginMember(loginMember.getLoginId(), loginMember.getPassword());
        // Validation 기능 작동-> 실패시 기존 로그인 사이트로 이동

        if(member==null){
            bindingResult.reject("globalError","아이디/비밀번호 입력 오류");
        }

        if(bindingResult.hasErrors()){
            return "/member/Login";
        }
        //성공시 DB에 존재하는 아이디, 비밀번호와 일치시(로그인 성공시) 게정 관련 임의의 KEY 값 생성하여 세션 저장. 홈페이지로 리다이렉트
        setLoginSession(request, member);
        return "redirect:/";
    }


    //로그인 세션 생성
    private void setLoginSession(HttpServletRequest request,Member member) {
        HttpSession session = request.getSession();
        //UUID : UUID + / +MEMBER 테이블 PK값
        String uuid = UUID.randomUUID().toString()+"/"+member.getId();
        if(member.getMemberSort().equals(MemberSort.ADMIN)){
            uuid ="admin"+uuid;
        }
        log.info("uuid={}",uuid);
        session.setAttribute(ConstEntity.SESSION,uuid); //사용자 SESSION 저장
        Long id = Long.valueOf(uuid.split("/")[1]);     //생성한 UUID에서 '/' 분류하여 USER_SESSION 저장
        session.setAttribute(ConstEntity.USER_SESSION,id);

    }

    //로그아웃 
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();   //기존에 존재하는 세션 값 무효화 시킴.
        }
        return "redirect:/";
    }

}
