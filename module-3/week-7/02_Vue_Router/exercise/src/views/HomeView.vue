<template>
    <div>
      <h1>Welcome to Book Store</h1>
      <section>
        <h2>Best Sellers</h2>
        <best-seller-list />
      </section>
  
      <section>
        <h2>New Releases</h2>
        <new-releases-list />
      </section>
    </div>
  </template>
  
  <script>
  import BestSellerList from '../components/BestSellerList.vue';
  import NewReleasesList from '../components/NewReleasesList.vue';
  
  export default {
    components: {
      BestSellerList,
      NewReleasesList,
    },
  };
  </script>
  
  <style scoped>
  section {
    margin-bottom: 2rem;
  }
  </style>
  