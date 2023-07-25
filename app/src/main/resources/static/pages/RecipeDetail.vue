<template>
  <div v-if="recipe">
    <h2>{{ recipe.name }}</h2>

    <div class="card mb-3">
      <div class="card-body">
        <h5 class="card-title">Instructions</h5>
        <p
          class="card-text"
          v-html="formatInstructions(recipe.instructions)"
        ></p>
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
            <!-- {{ ingredient.name }}: {{ ingredient.quantityString }} -->
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
    formatInstructions(instructions) {
      return instructions.replace(/\n/g, "<br>");
    },
  },
};
</script>
