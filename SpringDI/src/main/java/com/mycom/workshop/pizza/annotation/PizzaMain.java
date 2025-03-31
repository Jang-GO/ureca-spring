package com.mycom.workshop.pizza.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PizzaMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("workshop-xml/pizza-annotation.xml"); // 설정 xml 문서

        Chef chef = context.getBean("chef", Chef.class);
        chef.makePizza();

        HateCheeseChef chefOops = context.getBean("hateCheeseChef", HateCheeseChef.class);
        chefOops.makePizza();

        context.close();
    }
}
