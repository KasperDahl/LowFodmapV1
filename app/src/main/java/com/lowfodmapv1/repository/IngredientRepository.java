package com.lowfodmapv1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.lowfodmapv1.model.Ingredient;
import com.lowfodmapv1.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Repository for loading ingredient names from the JSON file.
 */
@Repository
public class IngredientRepository {
    // This is the path relative to the resource folder (app\src\main\resources).
    private static final String RECIPES_JSON_FILE = "recipes.json";

    private ObjectMapper objectMapper;

    @Autowired
    public IngredientRepository() {
        // this.recipeRepository = new RecipeRepository();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Load all (unique) ingredient names from the JSON file.
     *
     * @return List of all (unique) ingredient names.
     * @throws IOException If an error occurs reading the file.
     */
    public List<String> getAllIngredients() throws IOException {
        // Get a File object for the recipes file.
        // Return empty list if it doesn't exist.
        File file = new ClassPathResource(RECIPES_JSON_FILE).getFile();
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // CollectionType for deserialization
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        // Parse JSON file into Recipe objects
        List<Recipe> recipes = objectMapper.readValue(file, listType);

        // Iterate through each recipe and add each unique ingredient name to the set
        Set<String> uniqueIngredientNames = new HashSet<>();
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                uniqueIngredientNames.add(ingredient.getName());
            }
        }

        List<String> uniqueIngredientNamesList = new ArrayList<>(uniqueIngredientNames);
        return uniqueIngredientNamesList;
    }
}
