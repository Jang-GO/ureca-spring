package com.mycom.calc.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalcMain {
    public static void main(String[] args) {
        // Spring DI를 통한 객체 생성
        ApplicationContext context = new AnnotationConfigApplicationContext(CalcConfig.class); // 설정 Java Configuration (annotation)

        Calculator calculator = context.getBean("calculator", Calculator.class);
        System.out.println(calculator.add(3,7));
    }
}
