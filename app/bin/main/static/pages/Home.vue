<template>
    <div>
      <h1>Welcome to Low FODMAP Recipes!</h1>
      <button class="btn btn-primary" @click="$router.push('/allrecipes')">Show all recipes</button>
      <button class="btn btn-primary" @click="$router.push('/mealplan')">Meal Plan</button>
      <button class="btn btn-primary" @click="$router.push('/offers')">Offers</button>
      <button class="btn btn-primary" @click="$router.push('/shoppinglist')">Shopping List</button>
    </div>
  </template>
  
  <script>
  export default {};
  </script>
  