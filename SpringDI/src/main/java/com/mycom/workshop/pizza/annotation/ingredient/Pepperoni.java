package com.mycom.workshop.pizza.annotation.ingredient;

import org.springframework.stereotype.Component;

@Component
public class Pepperoni implements Ingredient {
    @Override
    public String addIngredient() {
        return "페퍼로니 ";
    }
}
