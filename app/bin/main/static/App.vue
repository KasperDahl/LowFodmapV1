<template>
    <div>
      <h1>Low FODMAP Recipes</h1>
      <button class="btn btn-primary" @click="$router.push('/allrecipes')">Show all recipes</button>
      <router-view></router-view>
    </div>
  </template>
  
  <script>
  export default {};
  </script>
  