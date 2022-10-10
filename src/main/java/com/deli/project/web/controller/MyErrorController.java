package com.deli.project.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class MyErrorController implements ErrorController {
    @GetMapping("/error")
    public String handlerError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int code = Integer.parseInt(status.toString());

        if(code== 500){
            return "error/500";
        }
        String referURL = request.getHeader("REFERER");

        referURL=referURL.substring(referURL.indexOf("b"));
        log.info("referURL={}",referURL);
       return referURL;
    }

    @ResponseBody
    @GetMapping("/error/500")
    public String error500(){
        return "오류 발생 다시 시도해주세요";
    }


//
//    @GetMapping("/nullDataError")
//    public void noChooseData(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String refer = redirectURL(request);
//        log.info("refer={}",refer);
//        response.sendRedirect(refer);
//
//    }
//
////    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
////    public ModelAndView errorHtml(HttpServletRequest request,HttpServletResponse){
////
////    }
//
//
//    private String redirectURL(HttpServletRequest request){
//        String referer = request.getHeader("REFERER");
//        log.info("refer",referer);
//        return referer;
//    }
//    private String subStrURL(String s){
//        String str = s.substring(s.indexOf("b") - 1);
//        log.info("str={}",str);
//        return str;
//    }
//
//
//
//    private void alert(HttpServletRequest request, HttpServletResponse response,String message) throws IOException {
//        String uri = request.getRequestURI();
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println("<script>alert('"+message+"'); location.href='"+uri+"';</script>");
//        out.flush();
//    }
//
//
//
//    @GetMapping("/AnotherException")
//    public void anotherEx(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String uri = request.getRequestURI();
//        alert(request,response,"오류가 발생했습니다.");
//    }


}
