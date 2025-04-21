package hello.springbootbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

// 1. '/'에 대한 매핑이 없고 localhost:8080 접속 => index.html 찾는다
//      없으면 /error 매핑을 찾는다. 이것마저 없으면 White label page 보여준다.
// 2. '/' 에 대한 매핑이 있고, 리턴 문자열이 "home.html" 이면 static 폴더에서 home.html 파일 찾는다.
@Controller
public class PageController {

    @GetMapping("/")
    public String home(){
        return "home.html";
    }

    // 페이지 요청
    @GetMapping("/login")
    public String login(){
        System.out.println("/login");
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password ){
        System.out.println(username +" , " + password);
        return "redirect:main.html";
    }


    @GetMapping("/login2")
    public String login2(){
        System.out.println("/login2");
        return "login2.html";
    }
    // ajax 요청
    @PostMapping("/login2")
    @ResponseBody
    public Map<String, String> login2(@RequestParam("username") String username, @RequestParam("password") String password ){
        return Map.of("result", "success");
    }
}
