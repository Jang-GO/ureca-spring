package hello.htmllogin.user.service;

import hello.htmllogin.user.dto.UserResultDto;
import hello.htmllogin.user.entity.User;

public interface UserService {
    UserResultDto insertUser(User user);
}
