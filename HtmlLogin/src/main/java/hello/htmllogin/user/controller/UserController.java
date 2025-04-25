package hello.htmllogin.user.controller;

import hello.htmllogin.user.dto.UserResultDto;
import hello.htmllogin.user.entity.User;
import hello.htmllogin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResultDto insertUser(User user){ // User Entity 살ㅇ
        return userService.insertUser(user);
    }
}
