package hello.htmllogin.webapp;

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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@WebMvcTest(UserController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class Test_DI_2 {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @MockitoBean
    UserRepository userRepository;

    @MockitoBean
    EntityManager entityManager;

    @MockitoBean
    HttpSession session;

    @MockitoBean
    HttpServletRequest request;

    @Test
    @Order(0)
    void testDI() {
        log.debug("testDI() 시작");
        assertNotNull(mockMvc);
        assertNotNull(userService);
        assertNotNull(userRepository);
        log.debug("testDI() 종료");
    }

    @Test
    @Order(1)
    void testDI_SessionRequest() {
        log.debug("testDI_SessionRequest 시작");
        assertNotNull(session);
        assertNotNull(request);
        log.debug("testDI_SessionRequest 종료");
    }

    // WebMvcTest기 때문에 jpa 관련 빈은 로딩 X
//    @Test
//    @Order(2)
//    void testDI_EntityManager() {
//        log.debug("testDI_EntityManager 시작");
//        assertNotNull(entityManager);
//
//        User user = entityManager.find(User.class, 1L);
//        assertNotNull(user);
//        log.info("{}", user);
//        log.debug("testDI_EntityManager 종료");
//    }
}
