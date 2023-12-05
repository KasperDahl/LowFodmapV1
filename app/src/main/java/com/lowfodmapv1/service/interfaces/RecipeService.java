package com.lowfodmapv1.service.interfaces;

import java.io.IOException;
import java.util.List;

import com.lowfodmapv1.model.Recipe;

/**
 * This interface defines the methods that the RecipeServiceImpl class must
 * implement.
 */
public interface RecipeService {
    List<Recipe> getAllRecipes() throws IOException;

    Recipe createRecipe(Recipe recipe) throws IOException;

    void deleteRecipe(String recipeName) throws IOException;
}
