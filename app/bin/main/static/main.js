async function loadSFC(url) {
  const options = {
    moduleCache: {
      vue: Vue
    },
    getFile(url) {
      return fetch(url).then((res) => res.text());
    },
    addStyle(styleStr) {
      const style = document.createElement('style');
      style.textContent = styleStr;
      document.head.appendChild(style);
    },
  };

  return await window['vue3-sfc-loader'].loadModule(url, options);
}

(async () => {
  const router = initRouter(loadSFC);

  const app = Vue.createApp({});
  app.use(router);
  app.mount('#app');
})();



/* async function loadSFC(url) {
    const options = {
      moduleCache: {
        vue: Vue
      },
      getFile(url) {
        return fetch(url).then((res) => res.text());
      },
      addStyle(styleStr) {
        const style = document.createElement('style');
        style.textContent = styleStr;
        document.head.appendChild(style);
      }
    };
  
    return await window['vue3-sfc-loader'].loadModule(url, options);
  }
  
  (async () => {
    const Home = await loadSFC('./pages/Home.vue');
    const AllRecipes = await loadSFC('./pages/AllRecipes.vue');
  
    const routes = [
      { path: '/', component: Home },
      { path: '/allrecipes', component: AllRecipes }
    ];
  
    const router = VueRouter.createRouter({
      history: VueRouter.createWebHashHistory(),
      routes,
    });
  
    const app = Vue.createApp({});
    app.use(router);
    app.mount('#app');
  })(); */
 