package hello.springbootbasic.controller;

// Json 응답 Json 요청 처리

import hello.springbootbasic.dto.CarDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JsonController {

    @GetMapping("/string")
    public String m1() {
        System.out.println("/string");
        return "안녕하세요!";
    }

    @GetMapping("/jsonstring")
    public String m2() {
        System.out.println("/string");
        String jsonStr = "\"result\":\"success";
        return "jsonStr";
    }

    @GetMapping("/map")
    public Map<String, String> m3() {
        System.out.println("/string");
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @GetMapping("/dto")
    public CarDto m4() {
        System.out.println("/dto");
        CarDto carDto = new CarDto("소나타", "장현서", 123);
        return carDto;
    }

    @GetMapping("/listdto")
    public List<CarDto> m5() {
        System.out.println("/listdto");
        return List.of(
                new CarDto("소", "장", 1234),
                new CarDto("소나", "장현", 1234),
                new CarDto("소나타", "장현서", 45));
    }

    // jackson 보다 gson이 LocalDateTime 처리가 편함
    @GetMapping("/localdatetime")
    public LocalDateTime m6(){
        return LocalDateTime.now();
    }

    // http request 에 json 파라미터 처리
    @PostMapping("/emp")
    public Map<String, String> m7(@RequestBody EmpDto empDto) {
        System.out.println("/emp");
        return Map.of("result", "success");
    }

    @PostMapping("/emplist")
    public Map<String, String> m8(@RequestBody List<EmpDto> empList) {
        System.out.println("/emplist");
        empList.forEach(System.out::println);
        return Map.of("result", "success");
    }
}
