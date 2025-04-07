package hello.phonemanager.common;

import hello.phonemanager.domain.Owner;
import hello.phonemanager.repository.OwnerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final OwnerRepository ownerRepository;

    public AuthorizationInterceptor(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthorizationInterceptor >>>>> " + request.getRequestURI());

        HttpSession session = request.getSession();
        Owner loginOwner = (Owner) session.getAttribute("loginOwner");

        Long shopId = null;
        try {
            shopId = Long.valueOf(request.getParameter("shopId"));
        } catch (Exception e) {
            response.sendRedirect("/"); // 잘못된 경로 접근시 루트로 리다이렉트
            System.out.println("shopId 파라미터가 잘못되었습니다.");
            return false;
        }

        Owner findOwner = ownerRepository.findByShopId(shopId);
        if(findOwner == null){
            response.sendRedirect("/");
            System.out.println("잘못된 경로에 접근하였습니다.");
            return false;
        }
        // 점주의 가맹점 ID와 요청 shopId가 일치하는지 확인
        if (!loginOwner.getId().equals(findOwner.getId())) {
            response.sendRedirect("/"); // 잘못 접근시 루트로 리다이렉트
            System.out.println("다른 점주의 가게에는 접근하지 마세요");
            return false;
        }

        return true;
    }
}
