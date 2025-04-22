package hello.springbootjpabasic.repository;

import hello.springbootjpabasic.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// repository 역할을 담당하는 구현 코드를 Spring Data JPA가 대신 생성, 수행
// extends JpaRepository 코드에 의해 대응되는 Entity 에 대한 기본 CRUD 및 기타 몇 가지 메소드가 자동 생성
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByName(String name);
    List<Student> findByEmailAndPhone(String email, String phone);
}
