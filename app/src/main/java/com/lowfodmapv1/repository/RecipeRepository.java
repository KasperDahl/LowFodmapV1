package com.lowfodmapv1.repository;

import com.lowfodmapv1.model.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* 
This class acts as an interface for accessing the recipes stored in the JSON database. 
The repository pattern allows you to separate the data access logic from the application, 
making it easier to switch between different storage methods (e.g., file, database) in the future.
 */

 public class RecipeRepository {

    private static final String RECIPES_JSON_FILE = "recipes.json";
    //private static final String RECIPES_JSON_FILE = "C:/LowFodmapV1/app/src/main/resources/recipes.json";
    
    private ObjectMapper objectMapper;

    public RecipeRepository() {
        objectMapper = new ObjectMapper();
    }

/*     /**
     * Load recipes from the JSON file.
     *
     * @return List of Recipe objects.
     * @throws IOException If there is an issue reading the file.
     
    public List<Recipe> loadRecipes() throws IOException {
        File file = new File(RECIPES_JSON_FILE);     

        if (!file.exists()) {
            return new ArrayList<>();
        }
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Recipe.class);
        return objectMapper.readValue(file, listType);
    } */

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
}
