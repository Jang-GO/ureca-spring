package com.mycom.workshop.pizza.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PizzaMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PizzaConfig.class);
        Chef chef = context.getBean("chef", Chef.class);
        chef.makePizza();
    }
}
