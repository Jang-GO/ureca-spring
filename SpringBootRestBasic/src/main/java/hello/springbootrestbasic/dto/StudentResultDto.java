package hello.springbootrestbasic.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class StudentResultDto {
    private String result;
    private StudentDto studentDto;
    private List<StudentDto> studentDtoList;
    private Long count;
}
