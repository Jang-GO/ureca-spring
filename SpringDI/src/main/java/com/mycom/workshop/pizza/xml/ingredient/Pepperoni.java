package com.mycom.workshop.pizza.xml.ingredient;

import org.springframework.stereotype.Component;

public class Pepperoni implements Ingredient{
    @Override
    public String addIngredient() {
        return "페퍼로니 ";
    }
}
