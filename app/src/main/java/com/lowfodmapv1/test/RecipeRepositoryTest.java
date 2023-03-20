package com.lowfodmapv1.test;

import com.lowfodmapv1.model.Recipe;
import com.lowfodmapv1.repository.RecipeRepository;

import java.io.IOException;
import java.util.List;

public class RecipeRepositoryTest {

    public static void main(String[] args) {
        RecipeRepository recipeRepository = new RecipeRepository();

        try {
            List<Recipe> recipes = recipeRepository.loadRecipes();
            System.out.println("Loaded recipes:");
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
                //System.out.println(recipe.getName());
                //System.out.println(recipe.getIngredients());
            }
        } catch (IOException e) {
            System.out.println("Error loading recipes: " + e.getMessage());
        }
    }
}
