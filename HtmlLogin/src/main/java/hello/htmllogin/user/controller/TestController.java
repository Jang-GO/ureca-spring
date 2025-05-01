package hello.htmllogin.user.controller;

import hello.htmllogin.user.dto.TestDto;
import hello.htmllogin.user.dto.TestResultDto;
import hello.htmllogin.user.dto.TestUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class TestController {

    // #1
    @GetMapping("/hello")
    public void hello(){
        log.info("hello");
    }

    @PostMapping("/param1")
    public void param1(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name
    ){
        log.info("param1");
        log.info("id : {}, name : {}", id, name);
    }

    @PostMapping("/param2")
    public void param2(@ModelAttribute TestDto testDto){
        log.info("param2");
        log.info("id : {}, name : {}", testDto.getId(), testDto.getName());
    }

    @PostMapping("/response1")
    public String response1(TestDto testDto){
        log.info("response1");
        log.info("id : {}, name : {}", testDto.getId(), testDto.getName());
        return "success";
    }

    @PostMapping("/response2")
    public TestResultDto response2(TestDto testDto){
        log.info("response2");
        log.info("id : {}, name : {}", testDto.getId(), testDto.getName());
        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setResult("success");
        return testResultDto;
    }

    @PostMapping("/response4")
    public TestResultDto response4(TestDto testDto){
        log.info("response4");
        log.info("id : {}, name : {}", testDto.getId(), testDto.getName());
        TestResultDto testResultDto = new TestResultDto();

        TestUserDto testUserDto = TestUserDto.builder()
                .id(1L)
                .name("홍길동")
                .email("hong@gildong.com")
                .phone(List.of("010-0000-0000", "010-1111-1111"))
                .build();

        testResultDto.setTestUserDto(testUserDto);
        testResultDto.setResult("success");
        return testResultDto;
    }
}
