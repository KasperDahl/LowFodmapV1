<template>
    <div>
      <h2>All Recipes</h2>
      <ul>        
        <li v-for="recipe in recipes" :key="recipe.id">{{ recipe.name }}</li>
      </ul>
    </div>
  </template>
  

  <script>  
  export default {
    data() {
      return {
        recipes: [],
      };
    },
    created() {
      this.fetchAllRecipes();
    },
    methods: {
        // Fetches all recipes from the API.
      fetchAllRecipes() {
        fetch('/api/recipes')
          .then((response) => {
            if (!response.ok) {
              throw new Error('HTTP error ' + response.status);
            }
            return response.json();
          })
          .then((data) => {
            this.recipes = data;
          })
          .catch((error) => {
            console.error('Error fetching recipes:', error);
          });
      },
    },
  };
  </script>
  