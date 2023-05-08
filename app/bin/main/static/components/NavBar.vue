<template>
    <nav class="custom-navbar">
      <div class="container">
        <router-link class="custom-navbar-brand" to="/">
          <img src="/assets/logo_LFD.jpg" alt="LFD" />
        </router-link>
        <ul class="custom-navbar-nav">
          <li class="custom-nav-item">
            <router-link class="custom-nav-link" to="/allrecipes">All Recipes</router-link>
          </li>
          <li class="custom-nav-item">
            <router-link class="custom-nav-link" to="/mealplan">Meal Plan</router-link>
          </li>
          <li class="custom-nav-item">
            <router-link class="custom-nav-link" to="/offers">Offers</router-link>
          </li>
          <li class="custom-nav-item">
            <router-link class="custom-nav-link" to="/shoppinglist">Shopping List</router-link>
          </li>
        </ul>
      </div>
    </nav>
  </template>
  
  <script>
  export default {};
  </script>
  
  <style>
  .custom-navbar {
    background-color: #f8f9fa; 
    padding: 0.5rem 0;
  }
  
  .container {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .custom-navbar-brand img {
    height: 40px;    
  }
  
  .custom-navbar-nav {
    display: flex;
    list-style-type: none;
    margin: 0;
    padding: 0;
  }
  
  .custom-nav-item {
    margin-left: 1rem;
  }
  
  .custom-nav-link {
    text-decoration: none;
    color: #333;
    font-weight: 500;
  }
  
  .custom-nav-link:hover {
    color: #007bff;
    text-decoration: none;
  }
  </style>
  

<!-- <template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container">
        <a class="navbar-brand" href="#" @click.prevent="$router.push('/')">Low FODMAP Recipes</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link class="nav-link" to="/allrecipes">Show all recipes</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/mealplan">Meal Plan</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/offers">Offers</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/shoppinglist">Shopping List</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </template>
  
  <script>
  export default {};
  </script> -->
  


<!-- <template>
    <nav>
      <button class="btn btn-primary" @click="$router.push('/')">Home</button>
      <button class="btn btn-primary" @click="$router.push('/allrecipes')">Show all recipes</button>
      <button class="btn btn-primary" @click="$router.push('/mealplan')">Meal Plan</button>
      <button class="btn btn-primary" @click="$router.push('/offers')">Offers</button>
      <button class="btn btn-primary" @click="$router.push('/shoppinglist')">Shopping List</button>
    </nav>
  </template>
  
  <script>
  export default {};
  </script> -->
  