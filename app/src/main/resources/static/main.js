/* document.getElementById("showAllRecipes").addEventListener("click", function() {
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
 */

// Create a Vue instance
const app = Vue.createApp({
    // Data object to hold the state of the application
    data() {
        return {
            // Array to store the fetched recipes
            recipes: []
        };
    },
    // Methods object to define functions for the application
    methods: {
        // Function to fetch and display all recipes
        showAllRecipes() {
            // Make a fetch request to the API endpoint
            fetch('/api/recipes')
                .then((response) => {
                    // Check if the response is okay, otherwise throw an error
                    if (!response.ok) {
                        throw new Error('HTTP error ' + response.status);
                    }
                    // Parse the response JSON data
                    return response.json();
                })
                .then((data) => {
                    // Store the fetched recipes in the recipes array
                    this.recipes = data;
                })
                .catch((error) => {
                    // Log any errors that occur during the fetch request
                    console.error('Error fetching recipes:', error);
                });
        }
    }
});

// Mount the Vue instance to the HTML element with the ID 'app'
app.mount('#app');

