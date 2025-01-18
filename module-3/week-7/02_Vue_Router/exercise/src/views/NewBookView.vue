<template>
    <div>
      <h1>Add a New Book</h1>
      <new-book-form />
    </div>
  </template>
  
  <script>
  import NewBookForm from '../components/NewBookForm.vue';
  
  export default {
    components: {
      NewBookForm
    }
  };
  </script>
  
  <style scoped>
  h1 {
    text-align: center;
    margin-bottom: 1rem;
  }
  </style>
  