<template>
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
</script>
