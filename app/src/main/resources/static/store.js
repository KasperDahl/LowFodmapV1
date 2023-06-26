const store = Vuex.createStore({
  state() {
    return {
      selectedRecipes: [],
    };
  },
  mutations: {
    saveRecipes(state, recipes) {
      state.selectedRecipes = recipes;
    },
  },
});

export default store;
