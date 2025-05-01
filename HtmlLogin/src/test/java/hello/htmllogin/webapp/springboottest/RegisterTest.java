package hello.htmllogin.webapp.springboottest;


import hello.htmllogin.user.controller.UserController;
import hello.htmllogin.user.dto.UserResultDto;
import hello.htmllogin.user.entity.User;
import hello.htmllogin.user.repository.UserRepository;
import hello.htmllogin.user.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterTest {

    // 개발 순서
    // repository -> service -> controller

    // userRepository test 는 User Entity 만 save
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional // 자동 롤백
    public void testRegister(){
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull();
    }

    // userRole 과 user entity save
    @Autowired
    private UserService userService;

    @Test
    @Transactional // 자동 롤백
    public void testRegister2(){
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");

        UserResultDto userResultDto = userService.insertUser(user);
        assertThat(userResultDto.getResult()).isEqualTo("success");
    }

    @Autowired
    private UserController userController;

    @Test
    @Transactional
    public void testRegister3(){
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@gildong.com");
        user.setPassword("1234");
        UserResultDto userResultDto = userController.insertUser(user);
        assertThat(userResultDto.getResult()).isEqualTo("success");
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void testRegister4() throws Exception {
        this.mockMvc.perform(
                post("/users/register")
                        .param("name","홍길동")
                        .param("email","hong@gildong.com")
                        .param("password", "1234")
        )
                .andExpect(status().isOk());
    }
}
