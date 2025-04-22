package hello.springbootjpabasic.service;

import hello.springbootjpabasic.entity.Student;
import hello.springbootjpabasic.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceFindImpl implements StudentServiceFind {
    private final StudentRepository studentRepository;
    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public List<Student> findByEmailAndPhone(String email, String phone) {
        return studentRepository.findByEmailAndPhone(email, phone) ;
    }
}
