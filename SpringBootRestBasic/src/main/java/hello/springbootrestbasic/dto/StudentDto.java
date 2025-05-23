package hello.springbootrestbasic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {
    private Integer id;
    private String name;
    private String email;
    private String phone;
}
