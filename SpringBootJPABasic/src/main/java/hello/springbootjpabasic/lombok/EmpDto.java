package hello.springbootjpabasic.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

//@NoArgsConstructor
@AllArgsConstructor
// 생성자는 롬복 어노테이션과 실제 코드가 중복되면 컴파일에러
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//// 생성자를 통해서 초기화 되어야 하는 final field 를 위한 생성자
//@RequiredArgsConstructor
@Data // Getter + Setter + ToString + EqualsAndHashCode

// 생성자 대신 Builder 패턴을 상룡하자 흐름 + Lombok 자체가 생성자 코드 눈에 보이지 않음
@Builder
@Slf4j
public class EmpDto {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String hireDate;

    final String departmentId;
}

