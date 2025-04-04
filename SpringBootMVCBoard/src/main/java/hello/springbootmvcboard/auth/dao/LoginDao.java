package hello.springbootmvcboard.auth.dao;

import hello.springbootmvcboard.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

// 사용자가 입력한 userEmail, userPassword 중 userEmail 로 select 해서 있으면 나머지 데이터를 포함해서 UserDto 객체를 없으면 null 리턴
// service layer 에서 사용자가 입력한 userEmail, userPassword 일치 여부 확인
@Mapper
public interface LoginDao {
    UserDto login(String userEmail);
}
