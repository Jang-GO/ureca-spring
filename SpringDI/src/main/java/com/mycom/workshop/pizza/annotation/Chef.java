package com.mycom.workshop.pizza.annotation;

import com.mycom.workshop.pizza.annotation.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Chef {
    private Ingredient ingredient;

    public Chef(@Qualifier("pepperoni") Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void makePizza(){
        System.out.println("[Chef] 피자 만들기 시작");
        System.out.println("[Chef] 피자 만드는중...");
        sleep(1000);

        String pizzaType = ingredient.addIngredient();
        System.out.println("[Chef] " + pizzaType + "피자 완성!");
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
