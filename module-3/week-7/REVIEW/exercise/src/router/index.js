import { createRouter as _createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

// Create routes 
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
 
]

export function createRouter () {
  return _createRouter({
    history: createWebHistory(),
    routes: routes
  })
}
