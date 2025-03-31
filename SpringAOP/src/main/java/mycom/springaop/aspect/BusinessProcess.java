package mycom.springaop.aspect;

import org.springframework.stereotype.Component;

/**
 * 개발자가 집중 개발하는 비즈니스 로직을 구현하는 클래스
 */

@Component
public class BusinessProcess {

    public void logic(){
        System.out.println("BusinessProcess.logic");
    }

    public void no_logic(){
        System.out.println("BusinessProcess.no_logic");
    }

    public int int_logic(){
        System.out.println("BusinessProcess.int_logic");
        return 0;
    }

    public int int_String_logic(String s1, int i){
        System.out.println("BusinessProcess.int_String_logic");
        return 0;
    }
}
