package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

// Spring 입장에서 MyClass 가 사용한 annotation (미리 약속된) 을 파악
public class Test {
    public static void main(String[] args) throws Exception{
//        Class<?> myClass = Class.forName("annotation.MyClass");
//        Annotation[] annotations = myClass.getAnnotations();
//
//        // AboutMe annotation 의 속성값을 확인/처리
//        for (Annotation annotation : annotations) {
//            if(annotation instanceof AboutMe aboutMe){
//                System.out.println(aboutMe.love());
//                System.out.println(aboutMe.hate());
//            }
//        }

        // Encrypt
        User user = new User("홍길동", "1234");
        System.out.println(user);

        // @Encrypt 를 사용한 필드가 있으면 해당 필드값을 암호화 변경
        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.isAnnotationPresent(Encrypt.class)){
                field.setAccessible(true); // private 필드에도 접근할 수 있음
                field.set(user, field.get(user)+"5678");
            }
        }

        System.out.println(user);
    }
}

// 워크샵 : 조원이 annotation.MyClass 처럼 특정 annotation(AboutMe) 을 사용하고, 속성값을 서로 다르게 준다.
//          그리고 애노테이션 사용하는 클래스 만들어(MyClass 처럼)
//          이후 MyClass.class 를 교환
//          그다음 리플렉션으로 확인해봐

// 1. 그냥 AboutMe, MyClass 로 하자
//    annotation 은 다 똑같이 하고 그걸 사용하는 MyClass 에서만 다르게 하면 되겠지
//    글고 꼭 .class 파일 받아서 해야함
// 2. Encrypt 같은거 해봐