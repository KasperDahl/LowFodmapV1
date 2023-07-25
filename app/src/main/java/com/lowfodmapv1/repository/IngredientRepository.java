package com.lowfodmapv1.repository;

import com.lowfodmapv1.model.Ingredient;
import com.lowfodmapv1.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredientRepository {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Ingredient> loadIngredients() {
        try {
            return recipeRepository.loadUniqueIngredients();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // returns an empty list in case of exception
        }
    }
}
