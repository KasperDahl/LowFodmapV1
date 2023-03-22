package com.lowfodmapv1.controller;

/* 
 * This class serves as a REST API controller for recipe-related endpoints. 
 * It handles HTTP requests and sends responses to the frontend.
 */

 import com.lowfodmapv1.repository.RecipeRepository;
 import com.lowfodmapv1.model.Recipe;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;
 
 import java.io.IOException;
 import java.util.List;
 
 // Marks this class as a RESTful web service controller
 @RestController
 // Sets the base URL path for all endpoints in this controller
 @RequestMapping("/api/recipes")
 public class RecipeController {
 
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
             // If there's an issue reading the file, print the stack trace and return an "Internal Server Error" status
             e.printStackTrace();
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }
 
     // Handles HTTP GET requests for the "/api/recipes/{id}" endpoint
     /* @GetMapping("/{id}")
     public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
         try {
             // Load all recipes from the JSON file
             List<Recipe> recipes = recipeRepository.loadRecipes();
             // Find the recipe with the given ID and return it, or null if not found
             Recipe recipe = recipes.stream()
                     .filter(r -> r.getId() == id)
                     .findFirst()
                     .orElse(null);
             // If the recipe is not found, return a "Not Found" status
             if (recipe == null) {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
             // If the recipe is found, return it with an "OK" status
             return new ResponseEntity<>(recipe, HttpStatus.OK);
         } catch (IOException e) {
             // If there's an issue reading the file, print the stack trace and return an "Internal Server Error" status
             e.printStackTrace();
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
     } */
 }
 
