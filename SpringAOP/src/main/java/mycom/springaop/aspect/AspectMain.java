package mycom.springaop.aspect;

import mycom.springaop.aspect.sub.SubBusinessProcess;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/aspect.xml");
        BusinessProcess bp = context.getBean("businessProcess", BusinessProcess.class);

        bp.logic();
        System.out.println("---------------------------------");
        bp.no_logic();
        System.out.println("---------------------------------");
        bp.int_logic();
        System.out.println("---------------------------------");
        bp.int_String_logic("s1",0);
        System.out.println("---------------------------------");

        SubBusinessProcess sbp = context.getBean("subBusinessProcess", SubBusinessProcess.class);
        sbp.logic();
        System.out.println("---------------------------------");

        context.close();
    }
}
