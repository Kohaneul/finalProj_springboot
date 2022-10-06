package com.deli.project.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@Slf4j
public class ErrorController {



    @GetMapping("/NoChooseData")
    public void noChooseData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
       alert(request,response,"데이터를 선택하지 않았습니다.");
    }

    private void alert(HttpServletRequest request, HttpServletResponse response,String message) throws IOException {
        String uri = request.getRequestURI();
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('"+message+"'); location.href='"+uri+"';</script>");
        out.flush();
    }



    @GetMapping("/AnotherException")
    public void anotherEx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        alert(request,response,"오류가 발생했습니다.");
    }


}
