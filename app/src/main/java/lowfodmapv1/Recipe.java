package lowfodmapv1;
import java.util.List;

public class Recipe {
    private String name;
    private String category;
    private List<Ingredient> ingredients;
    private List<String> cookingSteps;
    private int totalTimeMinutes;
    private int servings;

    public Recipe(String name, String category, List<Ingredient> ingredients,
                  List<String> cookingSteps, int totalTimeMinutes, int servings) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
        this.totalTimeMinutes = totalTimeMinutes;
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(List<String> cookingSteps) {
        this.cookingSteps = cookingSteps;
    }

    public int getTotalTimeMinutes() {
        return totalTimeMinutes;
    }

    public void setTotalTimeMinutes(int totalTimeMinutes) {
        this.totalTimeMinutes = totalTimeMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}

