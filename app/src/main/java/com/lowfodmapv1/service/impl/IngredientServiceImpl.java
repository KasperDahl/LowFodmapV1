package com.lowfodmapv1.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowfodmapv1.repository.IngredientRepository;
import com.lowfodmapv1.service.interfaces.IngredientService;

/**
 * This class implements the IngredientService interface and is used to retrieve
 * all (unique) ingredients from the database.
 */
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Sorts and returns all ingredients.
     * 
     * @return List of all ingredients.
     * @throws IOException if an I/O error occurs while retrieving the ingredients.
     */
    public List<String> getAllIngredients() throws IOException {
        List<String> allIngredients = ingredientRepository.getAllIngredients();
        Collections.sort(allIngredients);
        return allIngredients;
    }

}
