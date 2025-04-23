package hello.htmllogin.user.dto;

import hello.htmllogin.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;

    private Map <Integer, String> roles;

    public static UserDto from(User user, Map<Integer,String> roles){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
