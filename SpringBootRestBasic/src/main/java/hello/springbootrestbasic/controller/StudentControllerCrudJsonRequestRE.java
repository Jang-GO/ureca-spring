package hello.springbootrestbasic.controller;

import hello.springbootrestbasic.dto.StudentDto;
import hello.springbootrestbasic.dto.StudentResultDto;
import hello.springbootrestbasic.service.StudentServiceCrud;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ResponseEntity 만 사용
// ResponseEntity + ResultDto 함께 사용
//    1. ResultDto 를 Client 에게 전달, Client 가 Server 의 작업 결과를 ResultDto 를 통해서 처리
//    2. ResultDto 를 Client 에게 전달, 사용 X. 대신 Controller 에서 Service 에서 리턴한 ResultDto 객체 이용해서
//       ResponseEntity 의 응답 ㅋ모드를 결정
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/json/re")
public class StudentControllerCrudJsonRequestRE {
    private final StudentServiceCrud studentService;
    @GetMapping("/students")
    public ResponseEntity<StudentResultDto> listStudent(){
        StudentResultDto studentResultDto = studentService.listStudent();
        // ResponseEntity + ResultDto 함께 사용의 #1
        // 오류 없으면 ResultDto 를 body 에 추가, 200
        // 오류 있으면 ResultDto 를 body 에 추가 X, 에러 코드
        return new ResponseEntity<>(studentResultDto, HttpStatus.OK);
        // 500 에러로 status 코드를 보내도 body 에 데이터가 있으면 브라우저에서 예외 처리 X
//        return new ResponseEntity<>(studentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        // ResponseEntity 객체를 생성, 리턴하는 다른 표현
//        return ResponseEntity.status(HttpStatus.OK).body(studentResultDto);
//        return ResponseEntity.ok().body(studentResultDto);
//        return ResponseEntity.notFound().build();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResultDto> detailStudent(@PathVariable("id") int id){
        // ResponseEntity + ResultDot 함계 사용의 #2
        // Service 에서 리턴인 ResultDto 의 result 값을 확인하고 그에 맞는 ResponseEntity 객체 생성
        StudentResultDto studentResultDto = studentService.detailStudent(id);
        switch(studentResultDto.getResult()){
            case "success" -> {
                return ResponseEntity.ok().body(studentResultDto);
            }
            case "notfound" -> {
                return ResponseEntity.notFound().build();
            }
            default -> {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    // 등록, 수정에 사용되는 StudentDto를 Client에서 Json 으로 보낸다.
    @PostMapping("/students")
    public StudentResultDto insertStudent(@RequestBody StudentDto studentDto){
        return studentService.insertStudent(studentDto);
    }

    @PutMapping("/students/{id}")
    public StudentResultDto updateStudent(@PathVariable("id") int id,@RequestBody StudentDto studentDto){
        studentDto.setId(id);
        return studentService.updateStudent(studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public StudentResultDto deleteStudent(@PathVariable("id") int id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/studnets/count")
    public StudentResultDto countStudent() {
        return studentService.countStudent();
    }

    @GetMapping("/students/page")
    public StudentResultDto listStudent(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ){
        return studentService.listStudent(pageNumber, pageSize);
    }
}
