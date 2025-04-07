package hello.springbootmvcboard.config;

import hello.springbootmvcboard.common.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/index.html",
                        "favicon.ico",
                        "/assets/**",
                        "/pages/login", // page 요청
                        "/pages/register", // page 요청
                        "/auth/**", // 로그인 ajax 요청
                        "/users/**"
                );
    }
}
