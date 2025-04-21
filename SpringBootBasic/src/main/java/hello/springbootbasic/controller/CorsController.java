package hello.springbootbasic.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin("*") // 이 컨트롤러가 처리하는 모든 요청에 대해서 CORS 에 필요한 Header 를 내려준다.
// Get, Post : Well Known Request, 가장 일반적인 요청으로 백엔드가 이 요청을 지원하는 지 여부 확인 없이 바로 요청
// Put, Delete : 이전에 사용하지 않던 요청, 일반적이지 않은 요청 => 따라서 백엔드가 이 요청을 지원하는지 확인 (Options <- pre-flight)
//@CrossOrigin( => 설정파일로 작성
//        origins = "http://127.0.0.1:5500/",
//        allowCredentials="true",
//        allowedHeaders = "*",
//        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD}
//)
@RequestMapping("/cors")
public class CorsController {

    @GetMapping
    public Map<String, String> getCors(@RequestParam("param") String param){
        System.out.println("GET 요청: " + param);
        return Map.of("result", "success");
    }

    @PostMapping
    public Map<String, String> postCors(@RequestParam("param") String param){
        System.out.println("POST 요청: " + param);
        return Map.of("result", "success");
    }

    @PutMapping("/{id}")
    public Map<String, String> putCors(@PathVariable("id") Long id, @RequestParam Map<String, String> params){
        System.out.println("PUT 요청: id=" + id + ", param=" + params.get("param"));
        return Map.of("result", "success");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteCors(@PathVariable("id") Long id){
        System.out.println("DELETE 요청: id=" + id);
        return Map.of("result", "success");
    }
}
