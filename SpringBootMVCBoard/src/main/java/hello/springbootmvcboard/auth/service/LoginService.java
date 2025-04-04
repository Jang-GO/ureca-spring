package hello.springbootmvcboard.auth.service;

import hello.springbootmvcboard.user.dto.UserDto;

import java.util.Optional;

// LoginResultDto 를 만들지 않고 userDto 를 리턴하는 이유는 컨트롤러에서 session 처리를 하려고
public interface LoginService {
    Optional<UserDto> login(UserDto userDto);
}
