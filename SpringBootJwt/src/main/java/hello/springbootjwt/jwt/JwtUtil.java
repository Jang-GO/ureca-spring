package hello.springbootjwt.jwt;

import hello.springbootjwt.config.MyUserDetailsService;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

// jwt 생성. 검증 등
@Component
@Getter
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String secretKeyStr;
    private SecretKey secretKey;
    private final long tokenValidDuration = 1000L * 60 * 60 * 24;

    private final MyUserDetailsService userDetailsService;

    @PostConstruct
    private void init(){
        System.out.println(secretKeyStr);
        secretKey = new SecretKeySpec(secretKeyStr.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        System.out.println(secretKey);
    }

    // jwt 생성
    // username (subject) , role
    public String createToken(String username, List<String> roles){
        Date now = new Date();

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + tokenValidDuration))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    // UserDetailsService 를 통해 사용자 UserDetails 를 얻고
    // 이를 통해서 UsernamePasswordAuthenticationToken 객체를 만들어 리턴
    // 유효성 검증을 아래 메소드를 통해서 DB 를 통한 검증을 진행하는 건 token 발급 기간이 길면 발급시점의 UserDetails 와 현재 UserDetails 가 다를 수 있다는 점을 강조
    // 반대로 client 가 접속될 때마다 DB에 접근하는건 큰 부담
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
    }

    // jwt 로부터 username 추출
    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload()
                .getSubject();
    }

    // request 로부터 token 획득
    // client 가 header 에 "X-AUTH-TOKEN"
    public String getTokenFromHeader(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    // jwt 유효성 검증
    // 만료 일자만 검증
    public boolean validateToken(String token){
        return !Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload()
                .getExpiration().before(new Date());
    }


}
