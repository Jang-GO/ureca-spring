package com.mycom.workshop.pizza.annotation.ingredient;

import org.springframework.stereotype.Component;

@Component
public class Cheese implements Ingredient {
    @Override
    public String addIngredient() {
        return "치즈 ";
    }
}
