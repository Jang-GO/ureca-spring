package com.mycom.calc.all;

import com.mycom.calc.hasa.Calculator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component("aaa")
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int n1, int n2) {
        System.out.println("CalculatorImpl.add");
        return n1 + n2;
    }
}
