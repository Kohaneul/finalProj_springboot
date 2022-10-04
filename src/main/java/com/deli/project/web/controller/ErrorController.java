package com.deli.project.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ErrorController {
    @PostMapping("/NoChooseData")
    public void noChooseData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        log.info("requestURI={}",uri);
        out.println("<script>alert('선택하지 않았습니다. 다시 선택해주세요'); location.href='"+uri+"';</script>");
        out.flush();
    }


    @PostMapping("/AnotherException")
    public void anotherEx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        log.info("requestURI={}",uri);
        out.println("<script>alert('오류가 발생하였습니다. 다시 선택해주세요'); location.href='"+uri+"';</script>");
        out.flush();
    }


}
