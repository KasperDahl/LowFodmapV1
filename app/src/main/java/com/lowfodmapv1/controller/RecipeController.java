package com.lowfodmapv1.controller;

import com.lowfodmapv1.repository.RecipeRepository;
import com.lowfodmapv1.service.interfaces.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lowfodmapv1.model.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * This class serves as a REST API controller for recipe-related endpoints.
 * It handles HTTP requests and sends responses to the frontend via the base URL
 * endpoint "/api/recipes".
 */
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    // This is the path relative to the resource folder (app\src\main\resources).
    private static final String RECIPES_JSON_FILE = "recipes.json";

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Autowired
    private RecipeRepository recipeRepository;

    /*
     * Handles HTTP GET requests for the "/api/recipes" endpoint. Returns a list of
     * all recipes in the database. If there are no recipes, it returns a
     * "No Content" status.
     */
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            if (recipes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // You would inject a service class here, but for now, let's just use a
    // placeholder method
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) throws IOException {
        // This is where you'd usually call a method in your service class to handle
        // saving the recipe
        // For now, we're just going to return the recipe as is
        File FILE = new ClassPathResource(RECIPES_JSON_FILE).getFile();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Recipe> recipes;

        try {
            if (FILE.exists()) {
                // Read existing recipes
                recipes = mapper.readValue(FILE, new TypeReference<List<Recipe>>() {
                });
            } else {
                // File doesn't exist, initialize an empty recipe list
                recipes = new ArrayList<>();
            }

            // Add the new recipe
            recipes.add(recipe);

            // Write the updated list back to the file
            mapper.writeValue(FILE, recipes);
        } catch (IOException e) {
            // Handle the exception
            throw new RuntimeException("Could not open file " + FILE.getPath(), e);
        }

        return recipe;
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String name) {
        try {
            // Delete the recipe
            recipeRepository.deleteRecipe(name);

            // Return a "No Content" status to indicate successful deletion
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            // If there's an issue reading or writing the file, or the recipe is not found,
            // print the stack trace and return an "Internal Server Error" status
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}