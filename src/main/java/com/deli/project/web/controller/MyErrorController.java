package com.deli.project.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RequestMapping("/error")
public class MyErrorController implements ErrorController {


  @ExceptionHandler(NumberFormatException.class)
  public void NumberFormatEx(HttpServletRequest request, HttpServletResponse response, NumberFormatException e) throws IOException {
        String referURL = request.getHeader("REFERER");
        log.info("referURL={}",referURL);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
         referURL=referURL.substring(referURL.indexOf("b")-1);
        out.println("<script>alert('선택하지 않았습니다.'); location.href='"+referURL+"';</script>");
        log.info("referURL={}",referURL);
        out.flush();
  }

  @GetMapping("/500")
  public String error500(){
      return "/error/error-500";
  }



}
