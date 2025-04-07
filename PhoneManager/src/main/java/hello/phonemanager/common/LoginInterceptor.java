package hello.phonemanager.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor >>>>> " + request.getRequestURI());
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("loginOwner") == null) {
            response.sendRedirect("/owner/login"); // 로그인 페이지로 리다이렉트
            return false;
        }

        return true;
    }
}
