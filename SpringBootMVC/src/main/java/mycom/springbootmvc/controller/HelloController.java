package mycom.springbootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// client 의 request 를 받고 처리
@Controller // 이 애노테이션이 있는 클래스의 메소드들의 매핑 정보를 스프링이 수집해서 핸들러 매핑 자료구조에 저장
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        System.out.println("Hello....!!!!");
        return "hello"; // /WEB-INF/jsp/hello.jsp
    }
}
