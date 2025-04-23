package hello.htmllogin.auth.service;

import hello.htmllogin.user.dto.UserResultDto;

public interface LoginService {
    UserResultDto login(String email, String password);
}
