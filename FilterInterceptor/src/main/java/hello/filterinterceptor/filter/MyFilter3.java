package hello.filterinterceptor.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Spring 을 통해서 WAS 에 filter 등록
// @Component + implements Filter
//@Component
public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter3 >>> Before Filter Job");
        // filter 처리 (controller 이전 작업)
        // 작업 결과에 따라 통과, 거절 처리
        // 현재 이 filter는 무조건 통과

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("MyFilter3 >>> After Filter Job");
    }
}
