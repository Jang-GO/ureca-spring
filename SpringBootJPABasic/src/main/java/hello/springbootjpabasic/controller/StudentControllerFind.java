package hello.springbootjpabasic.controller;

import hello.springbootjpabasic.entity.Student;
import hello.springbootjpabasic.service.StudentServiceCrud;
import hello.springbootjpabasic.service.StudentServiceFind;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentControllerFind {
    private final StudentServiceFind studentService;

    @GetMapping("/find/name")
    public List<Student> findByName(@RequestParam("name") String name){
        return studentService.findByName(name);
    }

    @GetMapping("/find/emailandphone")
    public List<Student> findByName(@RequestParam("email") String email,@RequestParam("phone") String phone){
        return studentService.findByEmailAndPhone(email, phone);
    }
}
