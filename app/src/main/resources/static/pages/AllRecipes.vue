<template>
  <div>
    <h2>All Recipes</h2>
    <div class="sorting">
      <p>
        Sort by choosing an ingredient from the dropdown or write the
        ingredient's name yourself:
      </p>
      <select
        v-model="newIngredient"
        @change="addIngredient"
      >
        <option
          disabled
          value=""
        >Please select an ingredient</option>
        <option
          v-for="ingredient in allIngredients"
          :key="ingredient"
        >
          {{ ingredient }}
        </option>
      </select>
      <input
        v-model="newIngredient"
        type="text"
        placeholder="Enter an ingredient"
      />
      <button
        @click="addIngredient"
        class="btn btn-primary"
      >Add</button>
      <div
        v-for="ingredient in selectedIngredients"
        :key="ingredient"
      >
        <span>
          {{ ingredient }}
          <button @click="removeIngredient(ingredient)">x</button>
        </span>
      </div>
      <p v-if="noMatchingRecipes">
        There are no recipes containing all selected ingredients
      </p>
    </div>
    <ul>
      <li
        v-for="recipe in recipes"
        :key="recipe.name"
      >
        <router-link :to="'/recipe/' + recipe.name">{{
          recipe.name
        }}</router-link>
        <button
          @click="addRecipe(recipe)"
          class="btn btn-primary"
          style="float: right"
        >
          Add
        </button>
      </li>
    </ul>
    <h2>Chosen Recipes:</h2>
    <ul>
      <li
        v-for="recipe in selectedRecipes"
        :key="recipe.name"
      >
        <router-link :to="'/recipe/' + recipe.name">{{
          recipe.name
        }}</router-link>
        <button
          @click="removeRecipe(recipe)"
          class="btn btn-primary"
          style="float: right"
        >
          Remove
        </button>
      </li>
    </ul>
    <button
      @click="saveSelectedRecipes"
      class="btn btn-primary"
    >Save</button>
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
      selectedIngredients: [],
      newIngredient: "",
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
          this.filterRecipes(); // Filter recipes based on selected ingredients
        })
        .catch((error) => {
          console.error("Error fetching recipes:", error);
        });
    },
    filterRecipes() {
      // Filter the recipes based on the selected or input ingredients
      this.recipes = this.allRecipes.filter((recipe) => {
        return this.selectedIngredients.every((selectedIngredient) => {
          return recipe.ingredients.some((ingredient) => {
            return (
              ingredient.name.toLowerCase() === selectedIngredient.toLowerCase()
            );
          });
        });
      });
      this.noMatchingRecipes = this.recipes.length === 0; // Show the message if no matching recipes
    },
    addIngredient() {
      // Add the new ingredient to the selected ingredients if it is not already selected
      if (
        this.newIngredient &&
        !this.selectedIngredients.includes(this.newIngredient)
      ) {
        this.selectedIngredients.push(this.newIngredient);
        this.newIngredient = "";
      }
      this.filterRecipes();
    },
    removeIngredient(ingredient) {
      // Remove the given ingredient from the selected ingredients
      this.selectedIngredients = this.selectedIngredients.filter(
        (i) => i !== ingredient
      );
      this.filterRecipes();
    },
    addRecipe(recipe) {
      // Add the given recipe to the selected recipes if it is not already selected
      if (!this.selectedRecipes.includes(recipe)) {
        this.selectedRecipes.push(recipe);
      }
    },
    removeRecipe(recipe) {
      // Remove the given recipe from the selected recipes
      this.selectedRecipes = this.selectedRecipes.filter((r) => r !== recipe);
    },
    getAllIngredients() {
      // Fetch all unique ingredients from the API
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
