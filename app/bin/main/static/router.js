
function loadRoutes(loadSFC) {
    return [
      { path: '/', component: () => loadSFC('./pages/Home.vue') },
      { path: '/allrecipes', component: () => loadSFC('./pages/AllRecipes.vue') },
      { path: '/mealplan', component: () => loadSFC('./pages/MealPlan.vue') },
      { path: '/offers', component: () => loadSFC('./pages/Offers.vue') },
      { path: '/shoppinglist', component: () => loadSFC('./pages/ShoppingList.vue') },
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
  
  