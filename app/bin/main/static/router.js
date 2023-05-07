import { createRouter, createWebHistory } from 'vue-router';
import AllRecipes from './AllRecipes.vue';

const routes = [
  {
    path: '/allrecipes',
    name: 'AllRecipes',
    component: AllRecipes,
  },
];


console.log('Router created');
const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router;

