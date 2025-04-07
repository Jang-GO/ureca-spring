package hello.springbootmvcboard.auth.controller;

import hello.springbootmvcboard.auth.service.LoginService;
import hello.springbootmvcboard.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // LoginResultDto 대신 Map<String, String> 사용
    // login 성공하면 session 에 userDto 담는다.
    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(UserDto userDto, HttpSession session){
        Map<String, String> map = new HashMap<>();
        Optional<UserDto> optional = loginService.login(userDto);

        if(optional.isPresent()){
            UserDto dto = optional.get();
            session.setAttribute("userDto", dto); // session 은 timeout 이 존재 (디폴트 30분)

            map.put("result", "success");
        }else map.put("result", "fail");

        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
