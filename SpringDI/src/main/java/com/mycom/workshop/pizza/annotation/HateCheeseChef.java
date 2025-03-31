package com.mycom.workshop.pizza.annotation;

import com.mycom.workshop.pizza.annotation.ingredient.Cheese;
import com.mycom.workshop.pizza.annotation.ingredient.Ingredient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HateCheeseChef {
    private final List<Ingredient> ingredients;

    public HateCheeseChef(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void makePizza(){
        System.out.println("[HateCheeseChef] 피자 만들기 시작");
        System.out.println("[HateCheeseChef] 피자 만드는중...");
        sleep(1000);

        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            if(ingredient instanceof Cheese) continue;
            sb.append(ingredient.addIngredient());
        }

        System.out.println("[HateCheeseChef] " + sb + "피자 완성!");
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
