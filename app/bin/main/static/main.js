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
      }
    };
  
    return await window['vue3-sfc-loader'].loadModule(url, options);
  }
  
  (async () => {
    const Home = await loadSFC('./Home.vue');
    const AllRecipes = await loadSFC('./AllRecipes.vue');
  
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
  })();
  



/* // Import components from their respective files
const Home = httpVueLoader('./Home.vue');
const AllRecipes = httpVueLoader('./AllRecipes.vue');

const routes = [
  { path: '/', component: Home },
  { path: '/allrecipes', component: AllRecipes },
];

const router = VueRouter.createRouter({
  history: VueRouter.createWebHashHistory(),
  routes,
});

const app = Vue.createApp({});
app.use(router);
app.mount('#app');
 */




/* const AllRecipes = {
    template: `
      <div>
        <h2>All Recipes</h2>
        <ul>
          <li v-for="recipe in recipes" :key="recipe.id">{{ recipe.name }}</li>
        </ul>
      </div>
    `,
    data() {
      return {
        recipes: [],
      };
    },
    created() {
      fetch("/api/recipes")
        .then((response) => {
          if (!response.ok) {
            throw new Error("HTTP error " + response.status);
          }
          return response.json();
        })
        .then((data) => {
          this.recipes = data;
        })
        .catch((error) => {
          console.error("Error fetching recipes:", error);
        });
    },
  };
  
  const router = VueRouter.createRouter({
    history: VueRouter.createWebHistory(),
    routes: [
      {
        path: "/allrecipes",
        component: AllRecipes,
      },
    ],
  });
  
  const app = Vue.createApp({
    template: `
      <div>
        <h1>Low FODMAP Recipes</h1>
        <button class="btn btn-primary" @click="$router.push('/allrecipes')">Show all recipes</button>
        <router-view></router-view>
      </div>
    `,
  });
  
  app.use(router);
  app.mount("#app"); */
  





/* import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

console.log('Creating app');

const app = createApp(App);

console.log('Initializing Vue.js app');
app.use(router);
app.mount('#app');
console.log('Vue.js app initialized'); */




/* // Create a Vue instance
const app = Vue.createApp({
    // Data object to hold the state of the application
    data() {
        return {
            // Array to store the fetched recipes
            recipes: []
        };
    },
    // Methods object to define functions for the application
    methods: {
        // Function to fetch and display all recipes
        showAllRecipes() {
            // Make a fetch request to the API endpoint
            fetch('/api/recipes')
                .then((response) => {
                    // Check if the response is okay, otherwise throw an error
                    if (!response.ok) {
                        throw new Error('HTTP error ' + response.status);
                    }
                    // Parse the response JSON data
                    return response.json();
                })
                .then((data) => {
                    // Store the fetched recipes in the recipes array
                    this.recipes = data;
                })
                .catch((error) => {
                    // Log any errors that occur during the fetch request
                    console.error('Error fetching recipes:', error);
                });
        }
    }
});

// Mount the Vue instance to the HTML element with the ID 'app'
app.mount('#app');

 */