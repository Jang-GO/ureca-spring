package com.mycom.calc.hasa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMain {
    public static void main(String[] args) {
        // Spring DI를 통한 객체 생성
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/all-has-a-calc-annotation.xml"); // 설정 xml 문서
        HasaCalculator hasaCalculator = (HasaCalculator) context.getBean("hasaCalculator"); // name 기준 객체를 DI

        System.out.println(hasaCalculator.add(3,7));
        context.close();
    }
}
