package com.mycom.calc.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMain {
    public static void main(String[] args) {
        // Spring DI를 통한 객체 생성
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/calc-annotation.xml"); // 설정 xml 문서

//        Calculator calculator = context.getBean(Calculator.class); // id와 무관하게 객체를 DI
//        Calculator calculator = (Calculator) context.getBean("calculator"); // class 이름 기준 객체를 DI
        Calculator calculator = (Calculator) context.getBean("abc"); // name 기준 객체를 DI

        System.out.println(calculator.add(3,7));
        context.close();
    }
}
