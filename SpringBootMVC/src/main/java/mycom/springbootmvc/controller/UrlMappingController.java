package mycom.springbootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 다양한 url mapping 을 다뤄본다.
@Controller
public class UrlMappingController {

    @RequestMapping("/m1")
    public void m1(){
        System.out.println("/m1");
    }

    @RequestMapping(value = "/m2", method = RequestMethod.GET)
    public void m2(){
        System.out.println("/m2");
    }

    @RequestMapping(value = "/m2", method = RequestMethod.POST)
    public void m3(){
        System.out.println("/m3");
    }

    @GetMapping("/m4")
    public void m4(){
        System.out.println("/m4");
    }
    @PostMapping("/m5")
    public void m5(){
        System.out.println("/m5");
    }

    // path variable
    // client 가 요청시 보내는 데이터는 기본적으로 parameter 사용
    // 대신 요청 url의 일부에 데이터를 포함시켜서 보낼 수도 있다. (REST API)
    // ../books/7
    @GetMapping("/books/{bookId}")
    public void m6(@PathVariable int bookId){
        System.out.println("/m6 " + bookId);
    }

    // 목록 페이징
    @GetMapping("/list/{limit}/and/{offset}")
    public void m7(@PathVariable int limit, @PathVariable int offset){
        System.out.println("/m7 " + limit + " " + offset);
    }

    // 복수의 url mapping
    @PostMapping({"/url1","/url2"})
    public void m9(){
        System.out.println("굿굿");
    }

    // sub domain
    @PostMapping("/sub1/*")
    public void m10(){
        System.out.println("굿굿");
    }

    // 하위 모든것을 다 매핑하려면 * 2개쓰면 됨
    @PostMapping("/sub2/**")
    public void m11(){
        System.out.println("굿굿굿");
    }
}
