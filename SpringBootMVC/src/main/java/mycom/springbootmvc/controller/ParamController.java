package mycom.springbootmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import mycom.springbootmvc.dto.CarDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

// client -> server 로 전송하는 parameter 처리
// servlet, jsp <- request.getParameter
@Controller
public class ParamController {

    @GetMapping("/param1")
    public void m1(HttpServletRequest request) {
        System.out.println(request.getParameter("bookId"));
        System.out.println(request.getParameter("bookName"));
    }

    @GetMapping("/param2")
    public void m2(@RequestParam("bookId") String bookId) {
        System.out.println(bookId);
    }

    @GetMapping("/param3")
    public void m3(@RequestParam("bookId") int bookId, @RequestParam("bookName") String bookName) {
        System.out.println(bookId);
        System.out.println(bookName);
    }

    // 1. 파라미터 이름으로 매칭되지 않으면 null 처리
    // 2. 이때 메서드의 파라미터가 int면 에러 발생
    @GetMapping("/param4")
    public void m4(Integer book){
        System.out.println(book);
    }

    @GetMapping("/param5")
    public void m5(@RequestParam("bookName") String bookName){
        System.out.println(bookName);
    }

    // @RequestParam
    // required 속성 (default : true)
    @GetMapping("/param6")
    public void m6(@RequestParam(required = false, name="bookName") String bookName){
        System.out.println(bookName);
    }

    // parameter를 Dto로
    // int price 가 잘못 : 0으로 디폴트값
    // 기본생성자 X : 괜찮음 <- 다른생성자
    // 기본생성자 X, 다른생성자 X, setter X : 필드값 파라미터로 초기화 X
    // 기본생성자 X, 다른생성자 O, setter X : 다른생성자로 초기화 X

    // 결과적으로 스프링의 파라미터 자동화에서 Dto의 필드를 이해 <-- Setter, Getter 로 처리
    @GetMapping("/car")
    public void m7(CarDto carDto){
        System.out.println(carDto);
    }

    @GetMapping("/map")
    public void m8(@RequestParam Map<String, String> params){
        System.out.println(params.get("name"));
        System.out.println(params.get("price"));
    }

    // Header
    @GetMapping("/header")
    public void m9(@RequestHeader("User-Agent") String userAgent, @RequestHeader("API-KEY") String apikey){
        System.out.println(userAgent);
        System.out.println(apikey);
    }
}
