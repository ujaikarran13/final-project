import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import UserProfileView from '../views/UserProfileView.vue'
import FacilityView from '../views/FacilityView.vue'

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
      path: '/',
      name: 'HomeView',
      component: HomeView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
      meta: {
        requiresAuth: false
      } 
    },
    
    {
      path: '/facilities',
      name: 'facilities',
      component: FacilityView,
      meta: { requiresAuth: true 
      }
    },
    {
      path: '/facilities/:facilityId',
      name: 'FacilityView',
      component: FacilityView,
      meta: { requiresAuth: true 
      }
    },
    {
      path: '/user',
      name: 'UserProfileView',
      component: UserProfileView,
      meta: { requiresAuth: true 
      }
    }
  ];

// Create the router
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
