package hello.springbootjpabasic.service;

import hello.springbootjpabasic.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentServiceCrud {
    List<Student> listStudent();
    Optional<Student> detailStudent(int id);

    Student insertStudent(Student student);
    Optional<Student> updateStudent(Student student);
    void deleteStudent(int id);

    long countStudent();
    List<Student> listStudent(int pageNumber, int pageSize);
}
