package hello.springbootjpabasic.lombok;

public class Test {
    public static void main(String[] args) {
        EmpDto empDto = EmpDto.builder()
                .employeeId(1)
                .firstName("길동")
                .lastName("홍")
                .email("hong@gildong.com")
                .hireDate("2025-04-22")
                .departmentId("123")
                .build();

        System.out.println(empDto);
    }
}
