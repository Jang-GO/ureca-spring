package com.mycom.workshop.pizza.configuration.ingredient;

import org.springframework.stereotype.Component;

public class Hawaiian implements Ingredient {
    @Override
    public String addIngredient() {
        return "하와이안 ";
    }
}
