<template>
  <div>
    <h1>Shopping List</h1>
    <div v-if="selectedRecipes.length">
      <h2>You have chosen the following recipes:</h2>
      <ul>
        <li v-for="recipe in selectedRecipes" :key="recipe.name">
          {{ recipe.name }}
        </li>
      </ul>
      <button @click="generateShoppingList">Generate Shopping List</button>
      <div v-if="shoppingList.length">
        <h2>Your Shopping List:</h2>
        <ul>
          <li v-for="ingredient in shoppingList" :key="ingredient.name">
            {{ ingredient.quantityString }} {{ ingredient.name }}
            <button @click="removeIngredient(ingredient)">Remove</button>
          </li>
        </ul>
      </div>
    </div>
    <div v-else>
      <p>No recipes selected.</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedRecipes: [],
      shoppingList: [],
    };
  },
  created() {
    const selectedRecipesString = localStorage.getItem("selectedRecipes");
    if (selectedRecipesString) {
      this.selectedRecipes = JSON.parse(selectedRecipesString);
    }
  },
  methods: {
    generateShoppingList() {
      const shoppingList = {};
      for (const recipe of this.selectedRecipes) {
        for (const ingredient of recipe.ingredients) {
          if (shoppingList[ingredient.name]) {
            shoppingList[ingredient.name].quantity += ingredient.quantity;
            shoppingList[ingredient.name].quantityString =
              shoppingList[ingredient.name].quantity + " " + ingredient.unit;
          } else {
            shoppingList[ingredient.name] = {
              ...ingredient,
              quantity: ingredient.quantity,
              quantityString: ingredient.quantity + " " + ingredient.unit,
            };
          }
        }
      }
      this.shoppingList = Object.values(shoppingList);
    },
    removeIngredient(ingredient) {
      const index = this.shoppingList.indexOf(ingredient);
      if (index > -1) {
        this.shoppingList.splice(index, 1);
      }
    },
  },
};
</script>
