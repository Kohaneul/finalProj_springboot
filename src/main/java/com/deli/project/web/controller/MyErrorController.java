package com.deli.project.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 예외, 에러 발생시 처리하는 컨트롤러
 * */
@Slf4j
@ControllerAdvice
@RequestMapping("/error")
public class MyErrorController implements ErrorController {
    @GetMapping
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status!=null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error/400";
            } else {
                return "error/500";
            }


        }
        return "error/500";
    }



    //파라미터 값을 선택 안하게 되면 NumberFormatException이 발생하게 된다.
    //해당 예외가 발생하게 되면 처리해주는 Controller
  @ExceptionHandler(NumberFormatException.class)
  public void NumberFormatEx(HttpServletRequest request, HttpServletResponse response, NumberFormatException e) throws IOException {
      //이전 페이지로 redirect
        String referURL = request.getHeader("REFERER");
        log.info("referURL={}",referURL);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
         referURL=referURL.substring(referURL.indexOf("b")-1);
        out.println("<script>alert('선택하지 않았습니다.'); location.href='"+referURL+"';</script>");
        log.info("referURL={}",referURL);
        out.flush();
  }

    //500(INTERNAL SERVER ERROR 에러) 발생시
    @GetMapping("/500")
    public String error500(HttpServletResponse response){

      //상태코드를 500으로 바꾸고
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return "/error/error-500";    //해당 VIEW로 반환
  }
    //400(BAD_REQUEST 에러) 발생시
    @GetMapping("/400")
    public String error400(HttpServletResponse response){
        //상태코드를 400으로 바꾸고
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "/error/error-400";    //해당 VIEW로 반환
    }


}
