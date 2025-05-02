package hello.springbootsecurity.user.dto;

import hello.springbootsecurity.user.entity.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;

    private List<UserRole> roles;
}
