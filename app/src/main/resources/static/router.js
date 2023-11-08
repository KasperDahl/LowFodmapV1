function loadRoutes(loadSFC) {
  return [
    { path: "/", component: () => loadSFC("./pages/Home.vue") },
    { path: "/allrecipes", component: () => loadSFC("./pages/AllRecipes.vue") },
    { path: "/mealplan", component: () => loadSFC("./pages/MealPlan.vue") },
    { path: "/offers", component: () => loadSFC("./pages/Offers.vue") },
    { path: "/shoppinglist", component: () => loadSFC("./pages/ShoppingList.vue") },
    { path: "/uploadrecipe", component: () => loadSFC("./pages/UploadRecipe.vue") },
    { path: "/api/recipes/:name", component: () => loadSFC("./pages/RecipeDetail.vue") },
    { path: "/specialitems", component: () => loadSFC("./pages/SpecialItems.vue") },
    // Add more routes here
  ];
}

function initRouter(loadSFC) {
  const routes = loadRoutes(loadSFC);

  const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes,
  });

  return router;
}
