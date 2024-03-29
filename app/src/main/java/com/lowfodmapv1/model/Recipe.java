package com.lowfodmapv1.model;

import java.util.Collections;
import java.util.List;

/**
 * Model class for a recipe.
 */
public class Recipe {
    private String name;
    private List<String> instructions;
    private List<Ingredient> ingredients;
    private int servings;
    private String mealType;
    private boolean fullMeal;
    private int calories;
    private int timeToCook;
    private String comments;
    private String source;
    private boolean tried;

    public Recipe() {
    }

    public Recipe(String name, List<String> instructions, List<Ingredient> ingredients, int servings, String mealType,
            boolean fullMeal, int calories, int timeToCook, String comments, String source, boolean tried) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings;
        this.mealType = mealType;
        this.fullMeal = fullMeal;
        this.calories = calories;
        this.timeToCook = timeToCook;
        this.comments = comments;
        this.source = source;
        this.tried = tried;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInstructions() {
        return instructions != null ? instructions : Collections.emptyList();
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients != null ? ingredients : Collections.emptyList();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getMealType() {
        return mealType != null ? mealType : "";
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public boolean isFullMeal() {
        return fullMeal;
    }

    public void setFullMeal(boolean fullMeal) {
        this.fullMeal = fullMeal;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }

    public String getComments() {
        return comments != null ? comments : "";
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSource() {
        return source != null ? source : "";
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean getTried() {
        return tried;
    }

    public void setTried(boolean tried) {
        this.tried = tried;
    }
}
