// Below is code and comments from ChatGPT - it will need to be divided between respective classes - full conversation can be found on ChatGPT under the "Storing Recipe Data"

/* 
Sure! Here's an example of a Recipe class, along with a class that can be used to read the JSON data from a file and convert it to a list of Recipe objects


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


class RecipeReader {
    public static List<Recipe> readRecipesFromFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        byte[] jsonData = Files.readAllBytes(file.toPath());
        RecipeList recipeList = mapper.readValue(jsonData, RecipeList.class);
        return recipeList.getRecipes();
    }
}

class RecipeList {
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}


In this example, the Recipe class represents a single recipe, and the RecipeList class represents a list of recipes (which can be used to read the JSON data from a file). The RecipeReader class contains a static method that reads the JSON data from a file, converts it to a list of Recipe objects using the Jackson library's ObjectMapper, and returns the list.

To use this class in your Main class, you can call the readRecipesFromFile method and pass in the path to the JSON file:

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Recipe> recipes = RecipeReader.readRecipesFromFile("path/to/recipes.json");
            // Do something with the list of recipes...
        } catch (IOException e) {
            System.out.println("Error reading recipes from file: " + e.getMessage());
        }
    }
}

In this example, the Main class calls the readRecipesFromFile method and catches any IOException that might occur. You can then work with the list of Recipe objects as needed.

*/