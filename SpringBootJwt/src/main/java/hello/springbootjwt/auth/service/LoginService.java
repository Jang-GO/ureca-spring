package hello.springbootjwt.auth.service;

import hello.springbootjwt.auth.dto.LoginResultDto;

public interface LoginService {
    LoginResultDto login(String email, String password);

}
