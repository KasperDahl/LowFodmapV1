package com.lowfodmapv1.controller;

import com.lowfodmapv1.service.interfaces.IngredientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * This class display all the ingredients in the database at the
 * /api/ingredients endpoint.
 */
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /*
     * Handles HTTP GET requests for the "/api/ingredients" endpoint.
     * Returns a list of all ingredients in the database.
     * If there are no ingredients, it returns a "No Content" status.
     */
    @GetMapping
    public ResponseEntity<List<String>> getAllIngredients() {
        try {
            List<String> ingredients = ingredientService.getAllIngredients();
            if (ingredients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ingredients, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
