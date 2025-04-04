package hello.springbootmvcboard.user.controller;

import hello.springbootmvcboard.user.dto.UserDto;
import hello.springbootmvcboard.user.dto.UserResultDto;
import hello.springbootmvcboard.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    public UserResultDto register(UserDto userDto){
        return userService.registerUser(userDto);
    }
}
