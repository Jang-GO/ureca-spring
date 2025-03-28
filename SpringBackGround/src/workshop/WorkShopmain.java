package workshop;

import annotation.Baseball;

import java.lang.annotation.Annotation;

public class WorkShopmain {
    public static void main(String[] args) throws Exception{
        Class<?> myClass = Class.forName("annotation.BClass");
        Annotation[] annotations = myClass.getAnnotations();

        // AboutMe annotation 의 속성값을 확인/처리
        for (Annotation annotation : annotations) {
            if (annotation instanceof Baseball baseball) {
                System.out.println(baseball.love());
                System.out.println(baseball.hate());
            }
        }
    }
}
