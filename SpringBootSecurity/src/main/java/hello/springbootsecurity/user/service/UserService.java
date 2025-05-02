package hello.springbootsecurity.user.service;

import hello.springbootsecurity.user.dto.UserDto;
import hello.springbootsecurity.user.dto.UserResultDto;
import hello.springbootsecurity.user.entity.User;
import hello.springbootsecurity.user.entity.UserRole;
import hello.springbootsecurity.user.repository.UserRepository;
import hello.springbootsecurity.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResultDto insertUser(UserDto userDto){
        UserResultDto userResultDto = new UserResultDto();

        try {
            Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

            if( optionalUser.isPresent()  ) {
                userResultDto.setResult("exist");
                return userResultDto;
            }
            List<UserRole> userRoles = List.of(userRoleRepository.findByName("CUSTOMER")); // Spring Security 에서 Role 을 "Granted Authorities" 이름으로 저장하는데 자동으로 ROLE_ 를 붙인다.

            User user = new User();

            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            user.setPassword(encodedPassword);

            user.setUserRoles(userRoles); // User 와 UserRole 연결
            User savedUser = userRepository.save(user);
            userResultDto.setResult("success");

        }catch( Exception e ) {
            e.printStackTrace();
            userResultDto.setResult("fail");
        }

        return userResultDto;
    }
}