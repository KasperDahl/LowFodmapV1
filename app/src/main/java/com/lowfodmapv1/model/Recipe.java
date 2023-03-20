package com.lowfodmapv1.model;
import java.util.List;

// This class represents a single recipe with properties like name, instructions, 
// ingredients, numberOfPortions, mealType, entireMeal, calories, timeToCook, and comments.


public class Recipe {
    private String name;
    private String mealType;
    private List<Ingredient> ingredients;
    private List<String> instructions;
    private int totalTimeMinutes;
    private int servings;

    public Recipe(String name, String mealType, List<Ingredient> ingredients,
                  List<String> instructions, int totalTimeMinutes, int servings) {
        this.name = name;
        this.mealType = mealType;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.totalTimeMinutes = totalTimeMinutes;
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String category) {
        this.mealType = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> cookingSteps) {
        this.instructions = cookingSteps;
    }

    public int getTotalTimeMinutes() {
        return totalTimeMinutes;
    }

    public void setTotalTimeMinutes(int totalTimeMinutes) {
        this.totalTimeMinutes = totalTimeMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}

