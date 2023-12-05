package com.lowfodmapv1.service.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * This interface defines the methods that the IngredientServiceImpl class must
 * implement.
 */
public interface IngredientService {
    List<String> getAllIngredients() throws IOException;
}
