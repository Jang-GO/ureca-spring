package hello.springbootjwt.user.controller;

import hello.springbootjwt.user.dto.UserDto;
import hello.springbootjwt.user.dto.UserResultDto;
import hello.springbootjwt.user.service.UserService;
import lombok.RequiredArgsConstructor;
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
