package hello.springbootmvcboard.common;

import hello.springbootmvcboard.user.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private static final String JSON_STR = "{\"result\":\"login\"}";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor >>>>> {}", request.getRequestURI());
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if(userDto == null){
            // ajax 요청
            // client 가 header 에 "ajax":"true" <- board.jsp
            // {"result":"login"} json 문자열 return
            // client 의 javascript 에서 window.location.href 이용해서 페이지 이동
            if("true".equals(request.getHeader("ajax"))){
                log.info("LoginInterceptor >>>>> ajax request");
                response.getWriter().write(JSON_STR);
            }else{ // page 요청
                log.info("LoginInterceptor >>>>> page request");
                response.sendRedirect("/pages/login");
            }
            return false;
        }
        return true;
    }
}
