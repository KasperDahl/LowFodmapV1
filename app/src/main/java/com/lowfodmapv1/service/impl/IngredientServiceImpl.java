package com.lowfodmapv1.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowfodmapv1.repository.IngredientRepository;
import com.lowfodmapv1.service.interfaces.IngredientService;

@Service
// This class implements the IngredientService interface and is used to retrieve
// all (unique) ingredients from the database.
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    // This method returns all ingredients.
    public List<String> getAllIngredients() throws IOException {
        return ingredientRepository.getAllIngredients();
    }

}
