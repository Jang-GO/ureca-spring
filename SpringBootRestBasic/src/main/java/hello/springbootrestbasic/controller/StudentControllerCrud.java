package hello.springbootrestbasic.controller;

import hello.springbootrestbasic.dto.StudentDto;
import hello.springbootrestbasic.dto.StudentResultDto;
import hello.springbootrestbasic.service.StudentServiceCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentControllerCrud {
    private final StudentServiceCrud studentService;

    @GetMapping("/students")
    public StudentResultDto listStudent(){
        return studentService.listStudent();
    }

    @GetMapping("/students/{id}")
    public StudentResultDto detailStudent(@PathVariable("id") int id){
        return studentService.detailStudent(id);
    }

    @PostMapping("/students")
    public StudentResultDto insertStudent(StudentDto studentDto){
        return studentService.insertStudent(studentDto);
    }

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
