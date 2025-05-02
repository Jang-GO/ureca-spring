package hello.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    private final static String[] WHITE_LIST = { "/", "/index.html", "/csrf-token", "/", "/users/**", "/register.html", "/register" };
    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http,
            MyAuthenticationSuccessHandler successHandler,
            MyAuthenticationFailureHandler failureHandler
    ) throws Exception {
        return http
                // 권한에 대해서 httpRequest 를 어떻게 처리?
//                .authorizeHttpRequests( request -> request.anyRequest().permitAll()) // -> 전부 허락
//                .authorizeHttpRequests( request -> request.anyRequest().authenticated()) // -> 전부 인증
                .authorizeHttpRequests(
                        request -> {
                            request
                                    .requestMatchers(WHITE_LIST).permitAll()
                                    .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                                    .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                    .anyRequest().authenticated();
                        }
                )
//                .formLogin(Customizer.withDefaults()) // 디폴트 폼 로그인
                // csrf 설정
//                .csrf(csrf -> csrf.disable()) // 기능 off
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // cookie csrf token 을 내려준다.
                .formLogin(form ->
                        form
                                // 사용자 정의 로그인 페이지를 사용하면 기본적으로 csrf 를 전송하도록 구현해야 한다
                                // 만약 구현하지 않으면 csrf 토큰이 없다는 오류 발생, 로그인 처리 X
                                // csrf 를 무시하도록 설정도 가능
                                .loginPage("/login.html")
                                .loginProcessingUrl("/login")
                                // ajax 요청 처리를 하는 별도의 Handler
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
//                                .defaultSuccessUrl("/", true)
                                .permitAll()) // 다른 조건 없이 로그인 허락
                .logout(LogoutConfigurer::permitAll) // logout url 로 요청하면 자동으로 security 가 session을 invalidate
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
