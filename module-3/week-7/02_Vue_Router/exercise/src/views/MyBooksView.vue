<template>
    <div>
      <h1>My Reading List</h1>
      <reading-list />
      <div class="add-book-link">
        <router-link v-bind:to="{name: 'addBook'}">Add New Book</router-link>
      </div>
    </div>
  </template>
  
  <script>
  import ReadingList from '../components/ReadingList.vue';
  
  export default {
    components: {
      ReadingList
    }
  };
  </script>
  
  <style scoped>
  h1 {
    text-align: center;
    margin-bottom: 1rem;
  }
  </style>
  