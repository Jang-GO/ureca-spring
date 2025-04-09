package hello.phonemanager.config;

import hello.phonemanager.common.AuthorizationInterceptor;
import hello.phonemanager.common.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    public WebMvcConfig(LoginInterceptor loginInterceptor, AuthorizationInterceptor authorizationInterceptor) {
        this.loginInterceptor = loginInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",                    // 메인 페이지
                        "/owner/login",         // 로그인
                        "/owner/register",      // 회원가입
                        "/favicon.ico" // 정적 리소스
                );
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/shop/phones");
    }
}
