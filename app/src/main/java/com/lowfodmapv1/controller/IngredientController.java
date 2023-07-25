package com.lowfodmapv1.controller;

import com.lowfodmapv1.repository.IngredientRepository;
import com.lowfodmapv1.model.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public ResponseEntity<List<String>> getAllIngredients() {
        List<String> ingredients = ingredientRepository.loadIngredients();
        if (ingredients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }
}
