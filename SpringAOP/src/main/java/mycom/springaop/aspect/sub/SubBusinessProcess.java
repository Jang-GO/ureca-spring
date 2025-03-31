package mycom.springaop.aspect.sub;

import org.springframework.stereotype.Component;

@Component
public class SubBusinessProcess {

    public void logic(){
        System.out.println("SubBusinessProcess.logic");
    }
}
