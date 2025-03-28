package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 인터페이스 앞에 @ 붙여서 annotation 생성
// 어디에 사용할 것인가?(Target) TYPE, METHOD ...
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AboutMe {
    // 추상메소드로 annotation의 attribute 생성
    String love();
    String hate();
}
