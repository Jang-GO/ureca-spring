package hello.springbootsecurity.user.controller;

import hello.springbootsecurity.user.dto.UserDto;
import hello.springbootsecurity.user.dto.UserResultDto;
import hello.springbootsecurity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public UserResultDto insertUser(UserDto userDto){
        System.out.println("/users");
        return userService.insertUser(userDto);
    }


}
