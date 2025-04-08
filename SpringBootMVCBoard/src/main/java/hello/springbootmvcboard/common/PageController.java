package hello.springbootmvcboard.common;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
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
        return "board";
    }

    // 페이지 이름과 상관 없는 테스트 용도

    @GetMapping("/pages/json")
    @ResponseBody
    public LocalDateTime json(){
        return LocalDateTime.now();
    }
}
