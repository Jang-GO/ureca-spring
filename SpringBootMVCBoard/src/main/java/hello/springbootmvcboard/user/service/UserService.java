package hello.springbootmvcboard.user.service;

import hello.springbootmvcboard.user.dto.UserDto;
import hello.springbootmvcboard.user.dto.UserResultDto;

// UserResultDto 만들어서 리턴
public interface UserService {
    UserResultDto registerUser(UserDto userDto);
}
