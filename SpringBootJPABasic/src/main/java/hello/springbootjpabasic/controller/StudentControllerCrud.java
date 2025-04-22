package hello.springbootjpabasic.controller;

import hello.springbootjpabasic.entity.Student;
import hello.springbootjpabasic.service.StudentServiceCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentControllerCrud {
    private final StudentServiceCrud studentService;

    @GetMapping("/list")
    public List<Student> listStudent(){
        return studentService.listStudent();
    }

    @GetMapping("/detail/{id}")
    public Optional<Student> detailStudent(@PathVariable("id") int id){
        return studentService.detailStudent(id);
    }

    @PostMapping("/insert")
    public Student insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }

    @PostMapping("update")
    public Optional<Student> updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @GetMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") int id){
        studentService.deleteStudent(id);
    }

    @GetMapping("/count")
    public long countStudent() {
        return studentService.countStudent();
    }

    @GetMapping("/page")
    public List<Student> listStudent(
            @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageSize") Integer pageSize
    ){
        return studentService.listStudent(pageNumber, pageSize);
    }
}
