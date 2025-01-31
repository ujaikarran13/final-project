<template>
    <nav class="nav-list" v-if="this.$store.state.user.id">
      <div class="nav-link" ><router-link v-bind:to="{ name: 'HomeView' }">Home Page</router-link></div>
      <div class="nav-link" ><router-link v-bind:to="{ name: 'register' }">Register View</router-link></div>
      <div class="nav-link" ><router-link v-bind:to="{ name: 'facilities' }">Facilities View</router-link></div>
      <div class="nav-link" ><router-link v-bind:to="{ name: 'UserProfileView' }">User Dashboard</router-link></div>
      
    </nav>
  </template>
  
  <style scoped>
  /* Base nav styling is found in the global.css file.
   * This adds styling for the active nav link in Vue.
   */
  .router-link-exact-active {
    background-color: rgba(136, 193, 231, 0.5);
    color: black;
    font-weight: bold;
    text-decoration: underline;
  }
  .nav-link {
    font-size: 1.1rem;
  }
  .nav-list {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin: 20px 0;
  }
  .nav-link a {
  text-decoration: none; 
  padding: 5px 8px;
  border-radius: 5px;
  color: #333; 
  transition: background-color 0.3s ease, color 0.3s ease;
  }
  .nav-link a:hover{
  background-color: rgb(3, 214, 41);
  color: #000000;
  }

  
  </style>