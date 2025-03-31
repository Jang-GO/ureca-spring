package com.mycom.calc.hasa;

import org.springframework.stereotype.Component;

@Component
public class HasaCalculator {
    private Calculator calculator; // interface를 implements 한 객체가 주입 됨

    public HasaCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public int add(int a, int b) {
        System.out.println("HasaCalculator.add");
        return calculator.add(a,b);
    }
}
