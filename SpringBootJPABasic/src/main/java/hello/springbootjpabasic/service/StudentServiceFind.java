package hello.springbootjpabasic.service;

import hello.springbootjpabasic.entity.Student;

import java.util.List;

public interface StudentServiceFind {
    List<Student> findByName(String name);
    List<Student> findByEmailAndPhone(String email, String phone);
}
