package hello.springbootmvcboard.user.dao;

import hello.springbootmvcboard.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int registerUser(UserDto userDto);
}
