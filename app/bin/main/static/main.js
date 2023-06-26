async function loadSFC(url) {
  const options = {
    moduleCache: {
      vue: Vue,
    },
    getFile(url) {
      return fetch(url).then((res) => res.text());
    },
    addStyle(styleStr) {
      const style = document.createElement("style");
      style.textContent = styleStr;
      document.head.appendChild(style);
    },
  };

  return await window["vue3-sfc-loader"].loadModule(url, options);
}

(async () => {
  const NavBar = await loadSFC("./components/NavBar.vue");
  const router = initRouter(loadSFC);

  const app = Vue.createApp({});
  app.component("nav-bar", NavBar); // Register the NavBar component globally
  app.use(router);
  app.mount("#app");
})();
