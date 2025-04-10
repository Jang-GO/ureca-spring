package hello.springbootmvcboard.common;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class PageController {

    @GetMapping("/pages/register")
    public String register(){
        return "register";
    }

    @GetMapping("/pages/login")
    public String login(){
        return "login";
    }

    @GetMapping("/pages/board")
    public String board(){

        // 예외 처리 테스트
//        String s = null;
//        s.length();

        return "board";
    }

    // 페이지 이름과 상관 없는 테스트 용도
    @GetMapping("/pages/json")
    @ResponseBody
    public LocalDateTime json(){
        return LocalDateTime.now();
    }

    // 현재 컨트롤러 작업 수행 시 예외 발생 처리
    @ExceptionHandler(Exception.class)
    public String pageExceptionHandler(Exception e, Model model, HttpServletRequest request){
        model.addAttribute("exception", e);
        model.addAttribute("requestURI", request.getRequestURI());
        return "error";
    }
}
