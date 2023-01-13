package lowfodmapv1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeManager {
    private List<Recipe> recipes;

    public RecipeManager(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public HashMap<String, Integer> getAllIngredients() {
        HashMap<String, Integer> ingredients = new HashMap<>();
        for (Recipe recipe : recipes) {
            for (Map.Entry<String, Integer> ingredient : recipe.getIngredients().entrySet()) {
                String ingredientName = ingredient.getKey();
                Integer ingredientAmount = ingredient.getValue();
                if(ingredients.containsKey(ingredientName)) {
                    ingredients.put(ingredientName, ingredients.get(ingredientName) + ingredientAmount);
                } else {
                    ingredients.put(ingredientName, ingredientAmount);
                }
            }
        }
        return ingredients;
    }
}
