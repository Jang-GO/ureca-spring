package com.mycom.workshop.pizza.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PizzaMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("workshop-xml/pizza-xml.xml"); // 설정 xml 문서
        Chef chef = context.getBean("chef", Chef.class);
        chef.makePizza();
        context.close();
    }
}
