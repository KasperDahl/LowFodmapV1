package lowfodmapv1;
import java.util.HashMap;

public class Recipe {
    private String name;
    private HashMap<String, Integer> ingredients = new HashMap<>();
    private String instructions;

    public Recipe(String name, HashMap<String, Integer> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    
    
}
