package hello.springbootmvcboard.user.service;

import hello.springbootmvcboard.user.dao.UserDao;
import hello.springbootmvcboard.user.dto.UserDto;
import hello.springbootmvcboard.user.dto.UserResultDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResultDto registerUser(UserDto userDto) {
        UserResultDto userResultDto = new UserResultDto();
        if(userDao.registerUser(userDto) == 1) userResultDto.setResult("success");
        else userResultDto.setResult("fail");
        return userResultDto;
    }
}
