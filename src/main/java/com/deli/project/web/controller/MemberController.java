package com.deli.project.web.controller;

import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.UploadFile;
import com.deli.project.domain.repository.MemberSearch;
import com.deli.project.domain.service.MemberService;
import com.deli.project.web.controller.form.FileStore;
import com.deli.project.web.controller.form.MemberForm;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    private final FileStore fileStore;

    @GetMapping("/new")
    public String join(@ModelAttribute("memberForm") MemberForm memberForm){
        return "/member/Member";
    }

    @PostMapping("/new")
    public String join(@Valid @ModelAttribute("memberForm")MemberForm memberForm, BindingResult bindingResult, RedirectAttributes redirectAttributes,Model model,HttpServletResponse response) throws IOException {

        duplicatedCheck(memberForm.getLoginId(),memberForm.getNickName(),bindingResult);
        pwEqualCheck(memberForm.getPassword(), memberForm.getPasswordCheck(),bindingResult);
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.info("rejectedValue={}",fieldError.getRejectedValue());
                log.info("message={}",fieldError.getDefaultMessage());
            }
            return "/member/Member";
        }
        memberSave(memberForm, redirectAttributes, model);
        log.info(memberForm.getAttachFile().getOriginalFilename());

        alert(response);
        return "redirect:/";
    }

    private void alert(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('회원가입 완료 로그인 페이지로 이동합니다.'); location.href='/login';</script>");
        out.flush();
    }

    private void duplicatedCheck(String loginId,String nickName,BindingResult bindingResult){
        if(memberService.duplicatedLoginId(loginId)>0){
            bindingResult.reject("globalError","현재 사용중인 아이디 입니다.");
        }
        if(memberService.duplicatedNickName(nickName)>0){
            bindingResult.reject("globalError","현재 사용중인 닉네임 입니다.");
        }
    }


    private void memberSave(MemberForm memberForm, RedirectAttributes redirectAttributes, Model model) throws IOException {
        UploadFile attachFile = fileStore.uploadFile(memberForm.getAttachFile());

        Member member = Member.createMember(memberForm.getLoginId(), memberForm.getPassword(), memberForm.getNickName(), memberForm.getPhoneNumber(),
                memberForm.getMemberSort(),new Address(memberForm.getCity(), memberForm.getState(), memberForm.getZipCode()),attachFile);

        Long memberId = memberService.saveMember(member);

        model.addAttribute("member",memberService.findOne(memberId));
        redirectAttributes.addAttribute("id", memberId);
    }



    private void alertMember(HttpServletResponse response, RedirectAttributes redirectAttributes, Long memberId) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        redirectAttributes.addAttribute("id", memberId);
        out.println("<script>alert('멤버 입력 확인 창으로 이동합니다.'); location.href='/member/{id}';</script>");
        out.flush();
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id, Model model){
        Member member = memberService.findOne(id);
        model.addAttribute("member",member);
        return "/member/MemberCheck";
    }

    @ResponseBody
    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> member(@PathVariable Long id) throws MalformedURLException {
        Member member = memberService.findOne(id);
        String originFileName = member.getUploadFile().getOriginFileName();
        String serverFileName = member.getUploadFile().getServerFileName();
        UrlResource resource = new UrlResource("file:"+fileStore.fullPath(serverFileName));
        String encodedUploadFileName = UriUtils.encode(originFileName, StandardCharsets.UTF_8);
        String content = "attachment; filename=\"" +encodedUploadFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,content).body(resource);
    }



    @ResponseBody
    @GetMapping("/image/{attachFile}")
    public Resource image(@PathVariable String attachFile) throws MalformedURLException {
        return new UrlResource("file:"+fileStore.fullPath(attachFile));
    }


    @GetMapping("/all")
    public String memberAll(@ModelAttribute("memberSearch") MemberSearch memberSearch, Model model){
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
