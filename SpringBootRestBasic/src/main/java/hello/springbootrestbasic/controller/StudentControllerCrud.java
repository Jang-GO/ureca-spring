package hello.springbootrestbasic.controller;

import hello.springbootrestbasic.dto.StudentDto;
import hello.springbootrestbasic.dto.StudentResultDto;
import hello.springbootrestbasic.service.StudentServiceCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
@Tag(name="기본 student CRUD API", description = "Student 등록 수정 삭제 조회")
public class StudentControllerCrud {
    private final StudentServiceCrud studentService;

    @Operation(summary = "학생 목록 조회", description = "전체 학생을 조회합니다")
    @GetMapping("/students")
    public StudentResultDto listStudent(){
        log.debug("listStudent() debug");
        log.info("listStudent() debug");
        log.warn("listStudent() debug");
        return studentService.listStudent();
    }

    @Operation(summary = "학생상세 조회", description = "개별 학생을 조회합니다", deprecated = true)
    @GetMapping("/students/{id}")
    public StudentResultDto detailStudent(@PathVariable("id") int id){
        return studentService.detailStudent(id);
    }
    @Operation(summary = "학생 등록", description = "학생을 등록합니다", hidden = true)
    @PostMapping("/students")
    public StudentResultDto insertStudent(StudentDto studentDto){
        return studentService.insertStudent(studentDto);
    }

    @Operation(summary = "학생 수정", description = "학생을 수정합니다")
    @PutMapping("/students/{id}")
    public StudentResultDto updateStudent(@PathVariable("id") int id, StudentDto
            studentDto){
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
