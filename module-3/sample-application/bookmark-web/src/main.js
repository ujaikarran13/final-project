import { createApp } from 'vue'
import MyApp from './App.vue'
import { createStore } from './store'
import router from './router'
import UserService from './services/UserService'
import axios from 'axios'

// resets browser defaults
import '../public/css/reset.css';  
// sets global styles for both static & shared Vue components such as the app header
import '../public/css/global.css'; 

/* import fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faUser, faUsers, faUserCircle, faTrashCan, faPencil, faCirclePlus, faTriangleExclamation, faXmark, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons'

/* add icons to the library */
library.add(faUserCircle)
library.add(faUser)
library.add(faUsers)
library.add(faTrashCan)
library.add(faPencil)
library.add(faCirclePlus)
library.add(faTriangleExclamation)
library.add(faXmark)
library.add(faMagnifyingGlass)

/* sets the base url for server API communication with axios */
axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;

/*
 * The authorization header is set for axios when you login but what happens when 
 * you come back or the page is refreshed. When that happens you need to check 
 * for the token in local storage and if it exists you should set the header 
 * so that it will be attached to each request.
 */
let currentToken = localStorage.getItem('token')
let currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken && currentUser) {
  // Set token axios requests
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;

  // Make sure token is still valid
  UserService.getUserProfile(currentUser.id)
    .then(response => {
      // Update user data
      currentUser = response.data;
    })
    .catch( () => {
      // Token is not valid
      currentToken = null;
      currentUser = null;
    })
}

// Create the Vuex store passing in the stored credentials
const store = createStore(currentToken, currentUser);

const app = createApp(MyApp);
app.use(store);
app.use(router)
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app');
