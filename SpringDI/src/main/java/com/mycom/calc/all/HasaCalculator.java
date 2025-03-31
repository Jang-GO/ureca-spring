package com.mycom.calc.all;

import com.mycom.calc.hasa.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HasaCalculator {

//    @Autowired
//    @Qualifier("bbb")
    private Calculator calculator; // interface를 implements 한 객체가 주입 됨

    public HasaCalculator(@Qualifier("aaa") Calculator calculator) {
        this.calculator = calculator;
    }

    public int add(int a, int b) {
        System.out.println("HasaCalculator.add");
        return calculator.add(a,b);
    }
}
