package hello.htmllogin.user.repository;

import hello.htmllogin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // login
    // UserServiceImpl(#1, #2)
//    Optional<User> findByEmail(String email);

    // #3
    // jpql : 이메일로 찾을 때  select 가 2건 나가는 문제 해결
    @Query("select u from User u join fetch u.userRoles where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

}
