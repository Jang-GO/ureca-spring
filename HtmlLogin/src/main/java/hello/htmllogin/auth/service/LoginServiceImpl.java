package hello.htmllogin.auth.service;

import hello.htmllogin.user.dto.UserDto;
import hello.htmllogin.user.dto.UserResultDto;
import hello.htmllogin.user.entity.User;
import hello.htmllogin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public UserResultDto login(String email, String password) {
        UserResultDto userResultDto = new UserResultDto();

        // #1
        // id가 아닌 email 로 find 하기 때문에
        // EAGER 로 join 하여 가져오는게 아닌 2개의 select 쿼리 발생
//        Optional<User> optionalUser = userRepository.findByEmail(email);

        // #2
        // id값(4L) 로 하드코딩 테스트
        // join 을 통한 select
//        Optional<User> optionalUser = userRepository.findById(4L);

        // #3 jpql 이용
        Optional<User> optionalUser = userRepository.findByEmail(email);

        optionalUser.ifPresentOrElse(user -> {
            if (user.getPassword().equals(password)) {
                Map<Integer, String> roles = new HashMap<>();
                user.getUserRoles().forEach(userRole -> roles.put(userRole.getId(), userRole.getName()));
                UserDto userDto = UserDto.from(user, roles);

                userResultDto.setUserDto(userDto);
                userResultDto.setResult("success");
            } else {
                userResultDto.setResult("fail");
            }
        }, () -> {
            userResultDto.setResult("notfound");
        });

        log.debug("login() 종료");
        return userResultDto;
    }
}
