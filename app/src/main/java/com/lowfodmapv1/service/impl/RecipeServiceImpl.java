package com.lowfodmapv1.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowfodmapv1.model.Recipe;
import com.lowfodmapv1.repository.RecipeRepository;
import com.lowfodmapv1.service.interfaces.RecipeService;

/**
 * This class implements the RecipeService interface and is used to perform CRUD
 * operations on recipes.
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * Call the sortRecipesByName()-method before returning all recipes.
     * 
     * @return List of all recipes.
     * @throws IOException if an I/O error occurs while retrieving the recipes.
     */
    public List<Recipe> getAllRecipes() throws IOException {
        List<Recipe> allRecipes = recipeRepository.getAllRecipes();
        return sortRecipesByName(allRecipes);
    }

    /**
     * Creates a new recipe.
     * 
     * @param recipe The recipe to create.
     * @return The created recipe.
     * @throws IOException if an I/O error occurs while creating the recipe.
     */
    public Recipe createRecipe(Recipe recipe) throws IOException {
        return recipeRepository.createRecipe(recipe);
    }

    public void deleteRecipe(String recipeName) {

    }

    /**
     * Sorts recipes by name.
     * OBS! Maybe move this method closer to where the data is used (Vue.js)
     * 
     * @param recipes
     * @return List of sorted recipes.
     */
    public List<Recipe> sortRecipesByName(List<Recipe> recipes) {
        Collections.sort(recipes, new Comparator<Recipe>() {
            public int compare(Recipe r1, Recipe r2) {
                return r1.getName().compareTo(r2.getName());
            }
        });
        return recipes;
    }

}
