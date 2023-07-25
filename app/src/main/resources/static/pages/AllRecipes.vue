<template>
  <div>
    <h2>All Recipes</h2>
    <div class="sorting">
      <p>
        Sort by choosing an ingredient from the dropdown or write the
        ingredient's name yourself:
      </p>
      <select v-model="selectedIngredient" @change="fetchAllRecipes">
        <option disabled value="">Please select an ingredient</option>
        <option v-for="ingredient in allIngredients" :key="ingredient">
          {{ ingredient }}
        </option>
      </select>
      <input
        v-model="inputIngredient"
        type="text"
        placeholder="Enter an ingredient"
      />
      <button @click="fetchRecipesByIngredient" class="btn btn-primary">
        Add
      </button>
      <p v-if="noMatchingRecipes">
        There are no recipes containing {{ inputIngredient }}
      </p>
    </div>
    <p v-if="selectedIngredient || inputIngredient">
      Showing only recipes that include:
      {{ selectedIngredient || inputIngredient }}
    </p>
    <ul>
      <li v-for="recipe in recipes" :key="recipe.name">
        <input type="checkbox" :value="recipe" v-model="selectedRecipes" />
        <router-link :to="'/recipe/' + recipe.name">{{
          recipe.name
        }}</router-link>
      </li>
    </ul>
    <button @click="saveSelectedRecipes" class="btn btn-primary">Save</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      recipes: [],
      allRecipes: [], // Store all recipes to filter later
      allIngredients: [], // Store all unique ingredients
      selectedRecipes: [],
      selectedIngredient: "",
      inputIngredient: "",
      noMatchingRecipes: false,
    };
  },
  created() {
    this.fetchAllRecipes();
    this.getAllIngredients();
  },
  methods: {
    // Fetches all recipes from the API.
    fetchAllRecipes() {
      fetch("/api/recipes")
        .then((response) => {
          if (!response.ok) {
            throw new Error("HTTP error " + response.status);
          }
          return response.json();
        })
        .then((data) => {
          this.allRecipes = data; // Store all recipes
          this.filterRecipes(); // Filter recipes based on selected ingredient
        })
        .catch((error) => {
          console.error("Error fetching recipes:", error);
        });
    },
    filterRecipes() {
      // Filter the recipes based on the selected or input ingredient
      this.recipes = this.allRecipes.filter((recipe) => {
        return recipe.ingredients.some((ingredient) => {
          return (
            ingredient.name.toLowerCase() ===
            (this.selectedIngredient || this.inputIngredient).toLowerCase()
          );
        });
      });
      this.noMatchingRecipes = this.recipes.length === 0; // Show the message if no matching recipes
    },
    fetchRecipesByIngredient() {
      this.selectedIngredient = ""; // Clear selected ingredient
      this.filterRecipes();
    },
    getAllIngredients() {
      // Fetch all unique ingredients from the API
      // You'll need to create a new endpoint to get all ingredients, assuming '/api/ingredients'
      fetch("/api/ingredients")
        .then((response) => {
          if (!response.ok) {
            throw new Error("HTTP error " + response.status);
          }
          return response.json();
        })
        .then((data) => {
          this.allIngredients = data;
        })
        .catch((error) => {
          console.error("Error fetching ingredients:", error);
        });
    },
    saveSelectedRecipes() {
      localStorage.setItem(
        "selectedRecipes",
        JSON.stringify(this.selectedRecipes)
      );
      // Clear the selection
      this.selectedRecipes = [];
    },
  },
};
</script>

<!-- <template>
  <div>
    <h2>All Recipes</h2>
    <ul>
      <li v-for="recipe in recipes" :key="recipe.name">
        <input type="checkbox" :value="recipe" v-model="selectedRecipes" />
        <router-link :to="'/recipe/' + recipe.name">{{
          recipe.name
        }}</router-link>
      </li>
    </ul>
    <button @click="saveSelectedRecipes" class="btn btn-primary">Save</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      recipes: [],
      selectedRecipes: [],
    };
  },
  created() {
    this.fetchAllRecipes();
  },
  methods: {
    // Fetches all recipes from the API.
    fetchAllRecipes() {
      fetch("/api/recipes")
        .then((response) => {
          if (!response.ok) {
            throw new Error("HTTP error " + response.status);
          }
          return response.json();
        })
        .then((data) => {
          this.recipes = data;
        })
        .catch((error) => {
          console.error("Error fetching recipes:", error);
        });
    },
    saveSelectedRecipes() {
      localStorage.setItem(
        "selectedRecipes",
        JSON.stringify(this.selectedRecipes)
      );
      // Clear the selection
      this.selectedRecipes = [];
    },
  },
};
</script> -->
