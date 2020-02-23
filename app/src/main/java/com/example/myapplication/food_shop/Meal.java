package com.example.myapplication.food_shop;

public class Meal {
    private String step;
    private Ingredients ingredients;
    private Nutrient nutrient;


    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }
}
