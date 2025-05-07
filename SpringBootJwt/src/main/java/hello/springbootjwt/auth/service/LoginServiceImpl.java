package hello.springbootjwt.auth.service;

import hello.springbootjwt.auth.dto.LoginResultDto;
import hello.springbootjwt.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{

    // Spring Security 를 통한 로그인 ( <- 단순 이메일 패스워드 비교 뿐 아니라 시큐리티 관련 내부 처리가 함께 이루어져야 함)
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResultDto login(String email, String password) {
        LoginResultDto loginResultDto = new LoginResultDto();

        log.debug("login start");

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String username = authentication.getName();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).toList();

            String token = jwtUtil.createToken(username, roles);
            log.debug("create token : {}", token);

            loginResultDto.setResult("success");
            loginResultDto.setToken(token);

        }catch (Exception e){
            e.printStackTrace();

            loginResultDto.setResult("fail");
        }

        log.debug("login end");

        return loginResultDto;
    }
}
