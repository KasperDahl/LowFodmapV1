package com.lowfodmapv1.repository;

import com.lowfodmapv1.model.Ingredient;
import com.lowfodmapv1.model.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
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
    // This is the path relative to the resource folder (app\src\main\resources).
    private static final String RECIPES_JSON_FILE = "recipes.json";

    private ObjectMapper objectMapper;

    public RecipeRepository() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Loads recipes from the JSON file.
     *
     * @return List of Recipe objects.
     * @throws IOException If an error occurs reading the file.
     */
    public List<Recipe> loadRecipes() throws IOException {
        // Get a File object for the recipes file
        File file = new ClassPathResource(RECIPES_JSON_FILE).getFile();

        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // Construct a CollectionType object representing a list of Recipe objects.
        // This is required for Jackson to know what type to deserialize the JSON into.
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        // Use Jackson to parse the JSON file into a list of Recipe objects.
        // The readValue() method is called on the ObjectMapper object.
        return objectMapper.readValue(file, listType);
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
        // Get a File object for the recipes file
        File file = new ClassPathResource(RECIPES_JSON_FILE).getFile();

        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // CollectionType for deserialization
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        // Parse JSON file into Recipe objects
        List<Recipe> recipes = objectMapper.readValue(file, listType);

        // Initialize a set to store the unique ingredient names
        Set<String> uniqueIngredientNames = new HashSet<>();

        // Iterate through each recipe
        for (Recipe recipe : recipes) {
            // Iterate through each ingredient in the current recipe
            for (Ingredient ingredient : recipe.getIngredients()) {
                // Add the ingredient name to the set
                uniqueIngredientNames.add(ingredient.getName());
            }
        }

        // Convert the set to a list and sort it
        List<String> uniqueIngredientNamesList = new ArrayList<>(uniqueIngredientNames);
        Collections.sort(uniqueIngredientNamesList);

        // Return the sorted list
        return uniqueIngredientNamesList;
    }

    /**
     * Removes a recipe from the JSON file.
     *
     * @param name Name of the recipe to be removed.
     * @throws IOException If there is an issue reading or writing the file.
     */
    public void deleteRecipe(String name) throws IOException {
        File file = new ClassPathResource(RECIPES_JSON_FILE).getFile();

        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        List<Recipe> recipes = objectMapper.readValue(file, listType);

        // Find the recipe to delete
        Recipe recipeToDelete = recipes.stream()
                .filter(recipe -> recipe.getName().equals(name))
                .findFirst()
                .orElse(null);

        // If the recipe is not found, throw an exception
        if (recipeToDelete == null) {
            throw new IOException("Recipe not found");
        }

        // Remove the recipe from the list
        recipes.remove(recipeToDelete);

        // Write the updated list back to the file
        objectMapper.writeValue(file, recipes);
    }

}
