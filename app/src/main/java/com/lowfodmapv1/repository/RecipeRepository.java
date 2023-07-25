package com.lowfodmapv1.repository;

import com.lowfodmapv1.model.Ingredient;
import com.lowfodmapv1.model.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
This class acts as an interface for accessing the recipes stored in the JSON database. 
The repository pattern allows you to separate the data access logic from the application, 
making it easier to switch between different storage methods (e.g., file, database) in the future.
 */
@Repository
public class RecipeRepository {

    private static final String RECIPES_JSON_FILE = "recipes.json";
    // private static final String RECIPES_JSON_FILE =
    // "C:/LowFodmapV1/app/src/main/resources/recipes.json";

    private ObjectMapper objectMapper;

    public RecipeRepository() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Load recipes from the JSON file.
     *
     * @return List of Recipe objects.
     * @throws IOException If there is an issue reading the file.
     */
    public List<Recipe> loadRecipes() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RECIPES_JSON_FILE);

        if (inputStream == null) {
            return new ArrayList<>();
        }
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);
        return objectMapper.readValue(inputStream, listType);
    }

    /**
     * Save recipes to the JSON file.
     *
     * @param recipes List of Recipe objects to be saved.
     * @throws IOException If there is an issue writing the file.
     */
    public void saveRecipes(List<Recipe> recipes) throws IOException {
        objectMapper.writeValue(new File(RECIPES_JSON_FILE), recipes);
    }

    /**
     * Load unique ingredient names from the JSON file.
     *
     * @return List of unique ingredient names.
     * @throws IOException If there is an issue reading the file.
     */
    public List<String> loadUniqueIngredients() throws IOException {
        // Attempt to load the JSON file from the classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RECIPES_JSON_FILE);

        // If the file doesn't exist, return an empty list
        if (inputStream == null) {
            return new ArrayList<>();
        }

        // Construct a CollectionType object representing a list of Recipe objects
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        // Use Jackson to parse the JSON file into a list of Recipe objects
        List<Recipe> recipes = objectMapper.readValue(inputStream, listType);

        // Initialize a set to store the unique ingredient names
        Set<String> uniqueIngredientNames = new HashSet<>();

        // Iterate through each recipe
        for (Recipe recipe : recipes) {
            // Iterate through each ingredient in the current recipe
            for (Ingredient ingredient : recipe.getIngredients()) {
                // Add the name of the ingredient to the set.
                // Since a set automatically eliminates duplicates, only unique names will be
                // stored.
                uniqueIngredientNames.add(ingredient.getName());
            }
        }

        // Convert the set of unique ingredient names to a list
        List<String> uniqueIngredientNamesList = new ArrayList<>(uniqueIngredientNames);

        // Sort the list of unique ingredient names
        Collections.sort(uniqueIngredientNamesList);

        // Return the sorted list
        return uniqueIngredientNamesList;
    }

}
