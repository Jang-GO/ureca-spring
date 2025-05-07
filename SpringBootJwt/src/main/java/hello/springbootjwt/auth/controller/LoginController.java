package hello.springbootjwt.auth.controller;

import hello.springbootjwt.auth.dto.LoginResultDto;
import hello.springbootjwt.auth.service.LoginService;
import hello.springbootjwt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResultDto login(@RequestBody Map<String, String> loginData){
        return loginService.login(loginData.get("username"), loginData.get("password"));
    }
}
