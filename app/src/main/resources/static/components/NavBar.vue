<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <router-link class="navbar-brand" to="/">
        <img src="/assets/logo_LFD.jpg" alt="LFD" style="height: 50px" />
      </router-link>
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
            <router-link class="nav-link" to="/allrecipes"
              >All Recipes</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/uploadrecipe"
              >Upload Recipe</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/offers">Offers</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/shoppinglist"
              >Shopping List</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
