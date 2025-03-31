package com.mycom.calc.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMain {
    public static void main(String[] args) {
        // 개발자가 직접 객체 생성
//        Calculator calc = new Calculator();
//        System.out.println(calc.add(3,7));

        // Spring DI를 통한 객체 생성
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/calc-xml.xml"); // 설정 xml 문서

        Calculator calculator = context.getBean(Calculator.class); // id와 무관하게 객체를 DI
//        Calculator calculator = (Calculator) context.getBean("calculator"); // id 기준 객체를 DI

        System.out.println(calculator.add(3,7));
        context.close();
    }
}
