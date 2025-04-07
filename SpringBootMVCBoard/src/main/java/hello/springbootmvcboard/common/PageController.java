package hello.springbootmvcboard.common;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    // login controller 에 위치
//    @GetMapping("/pages/logout")
//    public String logout(HttpSession session){
//        session.invalidate();
//        return "login";
//    }
}
