package com.mycom.workshop.pizza.xml.ingredient;

import org.springframework.stereotype.Component;

public class Cheese implements Ingredient{
    @Override
    public String addIngredient() {
        return "치즈 ";
    }
}
