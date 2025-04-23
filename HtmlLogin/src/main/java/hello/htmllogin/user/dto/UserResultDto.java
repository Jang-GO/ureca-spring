package hello.htmllogin.user.dto;

import lombok.Data;

import java.util.List;

@Data
// @Builder XX
public class UserResultDto {
    private String result;
    private UserDto userDto;
    private List<UserDto> userList;
}
