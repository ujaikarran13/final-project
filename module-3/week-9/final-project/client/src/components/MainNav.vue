<template>
    <nav class="nav-list" v-if="this.$store.state.user.id">
      <div class="nav-link" ><router-link v-bind:to="{ name: 'facilities' }">Facilities</router-link></div>
    </nav>
  </template>
  
  <style scoped>
  /* Base nav styling is found in the global.css file.
   * This adds styling for the active nav link in Vue.
   */
  .nav-link .router-link-exact-active {
    background-color: rgba(136, 193, 231, 0.5);
    color: black;
    font-weight: bold;
  }
  </style>