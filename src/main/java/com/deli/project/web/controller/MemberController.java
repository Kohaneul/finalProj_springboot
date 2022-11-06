package com.deli.project.web.controller;

import com.deli.project.domain.ConstEntity;
import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.ImageFile;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.repository.MemberSearchDTO;
import com.deli.project.domain.service.MemberService;
import com.deli.project.web.controller.form.ImageStore;
import com.deli.project.web.controller.form.MemberForm;
import com.deli.project.web.controller.form.MemberUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
/**
 * 회원가입 컨트롤러
 * */
@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    private final ImageStore fileStore; //이미지파일 저장

    //회원가입
    @GetMapping("/new")
    public String join(@ModelAttribute("memberForm") MemberForm memberForm){
        return "/member/Member";
    }

    @PostMapping("/new")
    public String join(@Valid @ModelAttribute("memberForm")MemberForm memberForm, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletResponse response) throws IOException {

        duplicatedCheck(memberForm.getLoginId(),memberForm.getNickName(),bindingResult);
        pwEqualCheck(memberForm.getPassword(), memberForm.getPasswordCheck(),bindingResult);
        if(bindingResult.hasErrors()){
                //에러발생시 에러 발생한 원인과 값을 로그로 확인
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.info("rejectedValue={}",fieldError.getRejectedValue());
                log.info("message={}",fieldError.getDefaultMessage());
            }
            return "/member/Member";
        }
        //성공하면 멤버 저장
        memberSave(memberForm, redirectAttributes, model);
        //저장 완료되었다는 alert 창
        alert(response);
        return "redirect:/";
    }

    private void alert(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('회원가입 완료! 로그인 페이지로 이동합니다.'); location.href='/login';</script>");
        out.flush();
    }


    //아이디 & 닉네임 중복확인
    private void duplicatedCheck(String loginId,String nickName,BindingResult bindingResult){
        if(memberService.duplicatedLoginId(loginId)>0){ //기존 로그인아이디가 존재한다면 list.size로 반환되므로 0보다 클 것이다.
            //bindingResult 에 저장
            bindingResult.reject("globalError","현재 사용중인 아이디 입니다.");
        }
        if(memberService.duplicatedNickName(nickName)>0){ //기존 닉네임이 존재한다면 list.size로 반환되므로 0보다 클 것이다.
            bindingResult.reject("globalError","현재 사용중인 닉네임 입니다.");
        }
    }

    //멤버 저장
    private void memberSave(MemberForm memberForm, RedirectAttributes redirectAttributes, Model model) throws IOException {
        ImageFile attachFile = fileStore.uploadFile(memberForm.getAttachFile());
       // MultiPartFile의 이미지파일을 파일이름, 경로+서버이름 으로 반환함

        Member member = Member.createMember(memberForm.getLoginId(), memberForm.getPassword(), memberForm.getNickName(), memberForm.getPhoneNumber(),
                new Address(memberForm.getCity(), memberForm.getState(), memberForm.getZipCode()),attachFile,memberForm.getMemberSort());

        //반환한 파일을 Member 객체에 넣어주고 저장
        Long memberId = memberService.saveMember(member);
        model.addAttribute("member",memberService.findOne(memberId));
        redirectAttributes.addAttribute("id", memberId);
    }



    @GetMapping("/{id}")
    public String MyInfo(@SessionAttribute(name = ConstEntity.USER_SESSION) Long id, Model model){
        Member member = memberService.findOne(id);
        model.addAttribute("memberForm",member);
        return "/member/MemberCheck";
    }


    @GetMapping("/{id}/edit")
    public String editMember(@PathVariable Long id, Model model)
    {
        Member member = memberService.findOne(id);
        model.addAttribute("memberForm",new MemberUpdateForm(member.getId(),member.getLoginId(),member.getPassword(),member.getPassword(),member.getNickName(),null,member.getMemberSort(),member.getPhoneNumber(),
        member.getAddress().getCity(),member.getAddress().getState(),member.getAddress().getZipCode()));
        return "/member/updateMember";
    }


    @PostMapping("/{id}/edit")
    public String editMember(@PathVariable Long id,@Valid @ModelAttribute("memberForm")MemberUpdateForm memberUpdateForm,BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "/member/updateMember";
        }
        memberService.updateMember(id,memberUpdateForm);
        return "redirect:/member/{id}";
    }

    //http 요청의 body를 자바객체로 전달받음
    @ResponseBody
    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> member(@PathVariable Long id) throws MalformedURLException {
        Member member = memberService.findOne(id);  //아이디를 조회해서
        String originFileName = member.getUploadFile().getOriginFileName(); //기존 아이디 변수에 담아줌
        String serverFileName = member.getUploadFile().getServerFileName(); //서버에 저장된 파일주소, 아이디를 변수에 담아줌
        //fullPath : 현재 이미지 파일이 내 컴퓨터에 저장되어있는 경로
        //url 소스를 읽어온다.
        UrlResource resource = new UrlResource("file:"+fileStore.fullPath(serverFileName));
        //한글이름이 존재할 수도 있으니 utf-8로 인코딩
        String encodedUploadFileName = UriUtils.encode(originFileName, StandardCharsets.UTF_8);
        String content = "attachment; filename=\"" +encodedUploadFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,content).body(resource);
    }



    @ResponseBody
    @GetMapping("/images/{attachFile}")
    public Resource image(@PathVariable String attachFile) throws MalformedURLException {
        return new UrlResource("file:"+fileStore.fullPath(attachFile));
    }


    @GetMapping("/all")
    public String memberAll(@ModelAttribute("memberSearch") MemberSearchDTO memberSearch, Model model){
        List<Member> members = memberService.findAll(memberSearch);
        model.addAttribute("members",members);

        return "/member/MemberAll";
    }


    private void pwEqualCheck(String password, String passwordCheck, BindingResult bindingResult) {
        if(!password.equals(passwordCheck)){
            bindingResult.reject("globalError","비밀번호를 다시 입력해주세요");
        }
    }


}
