<template>
    <div class="doctors-list">
      <div v-for="doctor in doctors" :key="doctor.id" class="doctor-card">
        <h2>{{ doctor.id }}</h2>
        <p>{{ doctor.specialty }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    created(){
     this.$store.dispatch('fetchDoctors');
    },
    data() {
      return {
        
        doctors: []
        
      };
    }
  };
  </script>
  
  <style scoped>
  .doctor-list {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
  }
  .doctor-card {
    width: 30%;
    border: 1px solid #ddd;
    padding: 10px;
    border-radius: 5px;
    background-color: #f9f9f9;
  }
  </style>
  