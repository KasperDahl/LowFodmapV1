package com.lowfodmapv1.controller;

import com.lowfodmapv1.repository.RecipeRepository;
import com.lowfodmapv1.service.interfaces.RecipeService;
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

import java.io.IOException;
import java.util.List;

/**
 * This class serves as a REST API controller for recipe-related endpoints.
 * It handles HTTP requests and sends responses to the frontend via the base URL
 * endpoint "/api/recipes".
 */
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * Handles HTTP GET requests for the "/api/recipes" endpoint. Returns a list of
     * all recipes in the database. If there are no recipes, it returns a
     * "No Content" status.
     * 
     * @return List of all recipes in the database.
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

    /**
     * Creates a new recipe in the database. Returns the recipe that was created.
     * 
     * @param recipe
     * @return The recipe that was created.
     * @throws IOException If an error occurs reading or writing the file.
     */
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) throws IOException {
        try {
            recipeService.createRecipe(recipe);
            return new ResponseEntity<>(recipe, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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