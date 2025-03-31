package com.mycom.workshop.pizza.xml;

import com.mycom.workshop.pizza.xml.ingredient.Ingredient;

public class Chef {
    private Ingredient ingredient;

    public Chef(Ingredient ingredient) {
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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
