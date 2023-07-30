<template>

  <div v-if="recipe">
    <div class="d-flex justify-content-between align-items-center">
      <h2>{{ recipe.name }}</h2>
      <button
        class="btn btn-danger"
        @click="confirmDelete"
      >Delete Recipe</button>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Instructions</h5>
        <!-- Use v-for to loop over each instruction and display them -->
        <!-- Apply some css to handle long instructions and spacing between them -->
        <p
          class="card-text"
          v-for="(instruction, index) in recipe.instructions"
          :key="index"
          style="white-space: pre-line; margin-bottom: 1rem;"
        >
          {{ instruction }}
        </p>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Ingredients</h5>
        <ul class="list-group list-group-flush">
          <li
            class="list-group-item"
            v-for="ingredient in recipe.ingredients"
            :key="ingredient.name"
          >
            {{ ingredient.quantityString }} {{ ingredient.name }}
          </li>
        </ul>
      </div>
    </div>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Details</h5>
        <p class="card-text">
          <strong>Servings:</strong> {{ recipe.servings }}<br />
          <strong>Meal Type:</strong> {{ recipe.mealType }}<br />
          <strong>Full Meal:</strong> {{ recipe.fullMeal ? "Yes" : "No" }}<br />
          <strong>Calories:</strong> {{ recipe.calories }}<br />
          <strong>Time to Cook:</strong> {{ recipe.timeToCook }} minutes<br />
        </p>
      </div>
    </div>

    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Comments</h5>
        <p class="card-text">{{ recipe.comments }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      recipe: null,
    };
  },
  created() {
    this.fetchRecipeDetail();
  },
  methods: {
    fetchRecipeDetail() {
      const recipeName = this.$route.params.name;

      fetch("/api/recipes")
        .then((response) => response.json())
        .then((data) => {
          this.recipe = data.find((recipe) => recipe.name === recipeName);
        });
    },
    confirmDelete() {
      if (
        window.confirm(
          "Are you sure you want to delete this recipe? This action cannot be undone."
        )
      ) {
        this.deleteRecipe();
      }
    },
    deleteRecipe() {
      const recipeName = this.$route.params.name;
      fetch(`/api/recipes/${recipeName}`, { method: "DELETE" })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Failed to delete recipe.");
          }
          this.$router.push("/allrecipes"); // Redirect to main recipes page
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
