package com.deli.project.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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


  public String getErrorPath(){
      return "/error/error-400";
  }
    @GetMapping("/500")
    public String error500(HttpServletResponse response){
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return "/error/error-500";
  }

    @GetMapping("/400")
    public String error400(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "/error/error-400";
    }


}
