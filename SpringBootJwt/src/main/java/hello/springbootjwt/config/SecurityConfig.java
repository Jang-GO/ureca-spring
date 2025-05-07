package hello.springbootjwt.config;

import hello.springbootjwt.jwt.JwtAuthenticationFilter;
import hello.springbootjwt.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    private final static String[] WHITE_LIST = { "/", "/index.html", "/csrf-token", "/", "/users/**", "/register.html", "/register", "/login.html", "/login", "/favicon.ico" };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, MyAuthenticationEntryPoint myAuthenticationEntryPoint) throws Exception {
        return http
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(
                        request -> {
                            request
                                    .requestMatchers(WHITE_LIST).permitAll()
                                    .requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
                                    .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                    .anyRequest().authenticated();
                        }
                )
                // formLogin 방식에서는 허락되지 않는 요청에 대해 자동으로 login.html 페이지로 분기
                // formLogin 을 사용 X -> 예외 발생 -> json 응답 ( login 필요 )
                .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer.authenticationEntryPoint(myAuthenticationEntryPoint))

                // formLogin 방식에서는 Spring Security 가 자동으로 Filter 처리 ( UsernamePasswordAuthenticationFilter )
                // formLogin 사용 X ->  위 필터 앞에서 한 번 수행되는 jwt 인증 필터 적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
