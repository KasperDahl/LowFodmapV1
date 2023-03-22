document.getElementById("showAllRecipes").addEventListener("click", function() {
    fetch("/api/recipes")
        .then(function(response) {
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
            return response.json();
        })
        .then(function(recipes) {
            const recipeList = document.getElementById("recipeList");

            // Clear the list before adding new elements
            recipeList.innerHTML = '';

            // Loop through the recipes and create a list item for each recipe name
            recipes.forEach(function(recipe) {
                const listItem = document.createElement("li");
                listItem.textContent = recipe.name;
                recipeList.appendChild(listItem);
            });
        })
        .catch(function(error) {
            console.error("Error fetching recipes:", error);
        });
});
