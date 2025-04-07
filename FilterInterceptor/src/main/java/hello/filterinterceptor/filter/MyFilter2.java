package hello.filterinterceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Spring 을 통해서 WAS 에 filter 등록
// @Component + implements Filter
// filter 는 등록 시 순서 정해주지 않으면 클래스이름순으로 사전순으로 등록되네
//@Component
public class MyFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter2 >>> Before Filter Job");
        // 요청 url 체크
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if(httpServletRequest.getRequestURI().contains("/xyz")){
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 금지된 url 입니다.");
            return; // doFilter() X
        }

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("MyFilter2 >>> After Filter Job");
    }
}
