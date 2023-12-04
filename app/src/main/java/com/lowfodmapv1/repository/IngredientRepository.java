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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class IngredientRepository {
    // This is the path relative to the resource folder (app\src\main\resources).
    private static final String RECIPES_JSON_FILE = "recipes.json";

    // private RecipeRepository recipeRepository;
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
     * @throws IOException If there is an issue reading the file.
     */
    public List<String> getAllIngredients() throws IOException {
        // Get a File object for the recipes file
        File file = new ClassPathResource(RECIPES_JSON_FILE).getFile();

        // CollectionType for deserialization
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);

        // Parse JSON file into Recipe objects
        List<Recipe> recipes = objectMapper.readValue(file, listType);

        // Initialize a set to store the unique ingredient names
        Set<String> uniqueIngredientNames = new HashSet<>();

        // Iterate through each recipe and add each unique ingredient name to the set
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                uniqueIngredientNames.add(ingredient.getName());
            }
        }

        // Convert the set to a list and sort it
        List<String> uniqueIngredientNamesList = new ArrayList<>(uniqueIngredientNames);
        Collections.sort(uniqueIngredientNamesList);

        return uniqueIngredientNamesList;
    }
}
