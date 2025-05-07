package hello.springbootjwt.config;

import hello.springbootjwt.user.entity.User;
import hello.springbootjwt.user.entity.UserRole;
import hello.springbootjwt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

//    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    // 기본 form ui 로그인 그대로 (우리가 별도의 로그인 페이지 제공 X)
    // username, password 가 user / console password 사용 X <- UserDetailsService를 제공하므로
    // form ui 에 사용자가 입력한 username 값이 loadUserByUsername() 파라미터로 전달
    // DB를 통해서 (JPA 의 경우 UserRepository 를 거쳐서) username 으로 select username, password 를 가져와서
    // UserDetails 구현 객체를 만들어서 return 해줘야 한다.
    // UserDetails 구현 객체는 우선 SpringSecurity 에서 제공하는 User를 사용

    // ROLE 을 고려하지 않은 코드
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(passwordEncoder.getClass());
//
//        if("jang".equals(username)){
//            UserDetails build = User.builder()
//                    .username(username)
//                    .password(passwordEncoder.encode("1234"))
//                    .build();
//            return build;
//        }else{
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
    // Role 고려 O
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println(passwordEncoder.getClass());
//
//        if("jang".equals(username)){
//            return User.builder()
//                    .username(username)
//                    .password(passwordEncoder.encode("1234"))
//                    .roles("CUSTOMER")
//                    .build();
//        }else{
//            throw new UsernameNotFoundException("User not found");
//        }
//    }

    // 이젠 DB 에서 값을 가져와서 확인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<UserRole> userRoles = user.getUserRoles();

            List<SimpleGrantedAuthority> authorities = userRoles.stream().map(UserRole::getName).map(SimpleGrantedAuthority::new).toList();
            // user 와 userRoles로 userDetails 생성
            UserDetails userDetails = MyUserDetails.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .email(user.getEmail()) // user 의 다양한 정보를 추가
                    .authorities(authorities)
                    .build();

            return userDetails;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
