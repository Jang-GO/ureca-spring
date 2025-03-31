package com.mycom.workshop.pizza.annotation.ingredient;

import org.springframework.stereotype.Component;

@Component
public class Hawaiian implements Ingredient {
    @Override
    public String addIngredient() {
        return "하와이안 ";
    }
}
