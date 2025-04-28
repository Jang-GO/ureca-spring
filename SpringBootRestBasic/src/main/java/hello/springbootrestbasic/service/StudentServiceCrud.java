package hello.springbootrestbasic.service;

import hello.springbootrestbasic.dto.StudentDto;
import hello.springbootrestbasic.entity.Student;
import hello.springbootrestbasic.dto.StudentResultDto;

public interface StudentServiceCrud {
    StudentResultDto listStudent();
    StudentResultDto detailStudent(int id);
    StudentResultDto insertStudent(StudentDto studentDto);

    StudentResultDto updateStudent(StudentDto studentDto);
    StudentResultDto deleteStudent(int id);

    StudentResultDto countStudent();
    StudentResultDto listStudent(int pageNumber, int pageSize);
}
