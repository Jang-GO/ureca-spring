package hello.htmllogin.webapp.di;

// Spring Context 전체를 이용한 Test
// => @SpringbootTest
// Spring Web 을 이용한 Test
// => @WebMvcTest (JPA Context 등 사용 X)

import hello.htmllogin.user.controller.UserController;
import hello.htmllogin.user.entity.User;
import hello.htmllogin.user.repository.UserRepository;
import hello.htmllogin.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class Test_DI_1 {
    @Autowired
    UserController userController;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(0)
    void testDI(){
        log.debug("testDI() 시작");
        assertNotNull(userController);
        assertNotNull(userService);
        assertNotNull(userRepository);
        log.debug("testDI() 종료");
    }

    @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;

    @Test
    @Order(1)
    void testDI_SessionRequest(){
        log.debug("testDI()_SessionRequest 시작");
        assertNotNull(session);
        assertNotNull(request);
        log.debug("testDI()_SessionRequest 종료");
    }

    @Autowired
    EntityManager entityManager;

    @Test
    @Order(2)
    void testDI_EntityManager(){
        log.debug("testDI()_EntityManager 시작");
        assertNotNull(entityManager);

        User user = entityManager.find(User.class, 1);
        assertNotNull(user);
        log.info("{}",user);
        log.debug("testDI()_EntityManger 종료");
    }
}
