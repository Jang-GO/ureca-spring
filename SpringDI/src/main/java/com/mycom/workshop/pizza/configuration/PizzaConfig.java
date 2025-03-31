package com.mycom.workshop.pizza.configuration;

import com.mycom.workshop.pizza.configuration.ingredient.Cheese;
import com.mycom.workshop.pizza.configuration.ingredient.Hawaiian;
import com.mycom.workshop.pizza.configuration.ingredient.Ingredient;
import com.mycom.workshop.pizza.configuration.ingredient.Pepperoni;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PizzaConfig {

    @Bean
    public Ingredient cheese(){
        return new Cheese();
    }
    @Bean
    public Ingredient pepperoni(){
        return new Pepperoni();
    }
    @Bean
    public Ingredient hawaiian(){
        return new Hawaiian();
    }
    @Bean
    public Chef chef(){
        return new Chef(cheese());
    }
}
