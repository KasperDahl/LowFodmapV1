package com.lowfodmapv1.repository;

import com.lowfodmapv1.model.Recipe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for the performing CRUD operations on the JSON file containing the
 * recipes.
 */
@Repository
public class RecipeRepository {
    // This is the path relative to the resource folder (app\src\main\resources).
    private static String RECIPES_JSON_FILE = "recipes.json";

    private ObjectMapper objectMapper;

    public RecipeRepository() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Loads all recipes from the JSON file.
     *
     * @return List of all Recipe objects.
     * @throws IOException If an error occurs reading the file.
     */
    public List<Recipe> getAllRecipes() throws IOException {
        File FILE = new ClassPathResource(RECIPES_JSON_FILE).getFile();
        if (!FILE.exists()) {
            return new ArrayList<>();
        }

        // Constructs a CollectionType for a list of Recipe objects for JSON
        // deserialization.
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        // Parses JSON file into Recipe objects list using Jackson's readValue().
        return objectMapper.readValue(FILE, listType);
    }

    /**
     * Creates a new recipe and saves it to the JSON file.
     * 
     * @param recipe
     * @return The created recipe.
     * @throws IOException If an error occurs reading or writing the file.
     */
    public Recipe createRecipe(Recipe recipe) throws IOException {
        File FILE = new ClassPathResource(RECIPES_JSON_FILE).getFile();
        // Output the JSON with indentation for readability.
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Recipe> recipes;

        if (FILE.exists()) {
            // Read the existing recipes from the file.
            recipes = objectMapper.readValue(FILE, new TypeReference<List<Recipe>>() {
            });
        } else {
            recipes = new ArrayList<>();
        }
        recipes.add(recipe);

        // Write the updated recipe list back to the file.
        objectMapper.writeValue(FILE, recipes);
        return recipe;
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
     * Removes a recipe from the JSON file.
     *
     * @param name Name of the recipe to be removed.
     * @throws IOException If there is an issue reading or writing the file.
     */
    public void deleteRecipe(String name) throws IOException {
        File FILE = new ClassPathResource(RECIPES_JSON_FILE).getFile();

        // Constructs a CollectionType for a list of Recipe objects for JSON
        // deserialization.
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        // Find the recipe to delete from the original JSON file.
        List<Recipe> recipes = objectMapper.readValue(FILE, listType);
        Recipe recipeToDelete = recipes.stream()
                .filter(recipe -> recipe.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (recipeToDelete == null) {
            throw new IOException("Recipe not found");
        }

        // Remove the recipe from the list and write the updated list back to the file.
        recipes.remove(recipeToDelete);
        objectMapper.writeValue(FILE, recipes);
    }
}
