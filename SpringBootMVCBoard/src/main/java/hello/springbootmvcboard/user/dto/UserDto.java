package hello.springbootmvcboard.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private int userSeq;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userProfileImage;
    private Date userRegisterDate;
}
