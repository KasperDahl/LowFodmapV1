/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lowfodmapv1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class App { 
    
    public static void main(String[] args) {
        
        HashMap<String, Integer> ingredients1 = new HashMap<>();
        ingredients1.put("Meat", 500);
        ingredients1.put("Spelt", 100);
        ingredients1.put("Salt", 2);

        HashMap<String, Integer> ingredients2 = new HashMap<>();
        ingredients2.put("Meat", 300);
        ingredients2.put("Spelt", 200);
        ingredients2.put("Sausages", 400);

       /*  Recipe recipe1 = new Recipe("Meatball", ingredients1, "Cook them");
        Recipe recipe2 = new Recipe("Meat Sandwich", ingredients2, "Cook them");
 
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);
        
        RecipeManager rp = new RecipeManager(recipes);        

        System.out.println(rp.getAllIngredients()); 
        */
    }
}
