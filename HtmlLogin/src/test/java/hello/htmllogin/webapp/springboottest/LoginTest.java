package hello.htmllogin.webapp.springboottest;

import hello.htmllogin.auth.controller.LoginController;
import hello.htmllogin.auth.service.LoginService;
import hello.htmllogin.user.dto.UserResultDto;
import hello.htmllogin.user.entity.User;
import hello.htmllogin.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

//    @Autowired
//    private MockMvc mockMvc;

    // Controller
    @Autowired
    private LoginController loginController;

    @Autowired
    private HttpSession session;

    @Test
    public void testLogin(){
        UserResultDto userResultDto = loginController.login("user1@mycom.com", "password1", session);
        assertThat(userResultDto.getResult()).isEqualTo("success");
    }

    // Service
    @Autowired
    private LoginService loginService;

    @Test
    public void testLogin2(){
        UserResultDto login = loginService.login("user1@mycom.com", "password1");
        assertThat(login.getResult()).isEqualTo("success");
        assertThat(login.getUserDto()).isNotNull();
    }

    // repository
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLogin3(){
        Optional<User> optionalUser = userRepository.findByEmail("user1@mycom.com");
        assertThat(optionalUser.get()).isNotNull();
    }
}
