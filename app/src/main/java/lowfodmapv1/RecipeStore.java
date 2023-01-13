package lowfodmapv1;

import java.util.List;

public interface RecipeStore {
    void addRecipe(Recipe recipe);
    List<Recipe> getAllRecipes();
    Recipe getRecipe(String name);
    //void deleteRecipe(String name);    
}
