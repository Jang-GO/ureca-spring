package mycom.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


// Logging 을 담당하는 Aspect
@Component // 객체가 필요한 시점에 Spring 이 DI
@Aspect // aspectj 라이브러리에 의해 AOP 로 동작
public class LogAspect {

    // Logging 을 위한 객체 필요
    // trace > debug > info > warn > error
    // Spring Boot 의 현재 설정에 따라 로그 출력 결정 default 설정은 info
    // 따라서 trace랑 debug는 안나옴!
    // => application.properties 에서 설정 가능
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // PointCut( Join Point (모든 메소드) 중 어떤 메소드에 끼어 들 것인가를 표현 )
//    @Pointcut(value = "execution(* mycom.springaop.aspect.*.*(..))") // 리턴 패키지 클래스이름 파라미터
//    @Pointcut(value = "execution(int mycom.springaop.aspect.*.*(..))") // 리턴타입을 int 로 바꾸면 리턴타입이 int 인 메소드에만 적용!
//    @Pointcut(value = "execution(int mycom.springaop.aspect.*.*(String, int))") // int 리턴타입, (String, int) 파라미터인 메소드에 적용
//    @Pointcut(value = "execution(* mycom.springaop.aspect.sub.*.*(..))") // sub 패키지에 있는 메소드에만 적용
    @Pointcut(value = "execution(* mycom.springaop.aspect..*.*(..))") // aspect 패키지 및 그 하위 패키지 전부
    private void logPointcut() { // 이름이 logPointcut() 인 Pointcut 1개 생성
    }

    // Advise 와 JoinPoint 에 의해 실제 로그 구현
    @Before("logPointcut()")
    public void logBefore(JoinPoint joinPoint) { // JoinPoint 는 실제 호출되는 메소드
//        System.out.println("[SYSTEM.OUT.PRINT : before]");
        logger.info("[LOGGER : before]");
        logger.info(joinPoint.getSignature().getName());
    }

    @After("logPointcut()")
    public void afterLog(JoinPoint joinPoint){
//        System.out.println("[SYSTEM.OUT.PRINT : after]");
        logger.debug("[LOGGER : after]");
    }
}
