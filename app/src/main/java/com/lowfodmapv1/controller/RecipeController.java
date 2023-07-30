package com.lowfodmapv1.controller;

/* 
 * This class serves as a REST API controller for recipe-related endpoints. 
 * It handles HTTP requests and sends responses to the frontend.
 */

import com.lowfodmapv1.repository.RecipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lowfodmapv1.model.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
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

// Marks this class as a RESTful web service controller
@RestController
// Sets the base URL path for all endpoints in this controller
@RequestMapping("/api/recipes")
public class RecipeController {

    private static final File FILE = new File("src/main/resources/recipes.json");

    // Automatically injects the RecipeRepository instance into the controller
    @Autowired
    private RecipeRepository recipeRepository;

    // Handles HTTP GET requests for the "/api/recipes" endpoint
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        try {
            // Load all recipes from the JSON file
            List<Recipe> recipes = recipeRepository.loadRecipes();
            // If the list of recipes is empty, return a "No Content" status
            if (recipes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // If the list of recipes is not empty, return the list with an "OK" status
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (IOException e) {
            // If there's an issue reading the file, print the stack trace and return an
            // "Internal Server Error" status
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // You would inject a service class here, but for now, let's just use a
    // placeholder
    // private RecipeService recipeService;
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        // This is where you'd usually call a method in your service class to handle
        // saving the recipe
        // For now, we're just going to return the recipe as is
        // return recipeService.saveRecipe(recipe);
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