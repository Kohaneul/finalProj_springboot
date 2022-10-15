package com.deli.project.domain.interceptor;
import com.deli.project.domain.ConstEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
/**
 * 관리자 전용 Interceptor.
 * 멤버 구분 -> admin 으로 설정시 관리자 세션은 세션 id에 "admin"으로 설정함
 * 회원 관련 정보를 조회, 수정, 삭제 가능
 * */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        String uuid = session.getAttribute(ConstEntity.SESSION).toString();
        if(!uuid.contains("admin")){
            response.setContentType("text/html; charset=UTF-8");
            response.sendRedirect("/");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('접근 권한이 없습니다.'); location.href='/';</script>");
            out.flush();
            return false;
        }
        return true;
    }

}
