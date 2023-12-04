package com.lowfodmapv1.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowfodmapv1.model.Recipe;
import com.lowfodmapv1.repository.RecipeRepository;
import com.lowfodmapv1.service.interfaces.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() throws IOException {
        // JEG VIL TRO DET EVT ER HER JEG EVT KUNNE SORTERE OPSKRIFTERNE ALFABETISK TIL
        // AT STARTE MED!!!
        return recipeRepository.getAllRecipes();
    }

    public Recipe creatRecipe(Recipe recipe) {
        return null;
    }

    public void deleteRecipe(String recipeName) {

    }

}
