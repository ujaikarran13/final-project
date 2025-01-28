<template>
    <div class="facility-card">
      <h2>{{ facility.id }}</h2>
        <p>{{ facility.name }}</p>
        <p><strong>Address</strong> {{ facility.address }}</p>
        <button @click="handleClick">More Info</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'FacilityCard',
    props: {
      facility: {
        type: Object,
        required: true,
      },
    },
   methods: {
    handleClick(){
      alert('More info about facility ' + this.facility.name);
    }
   } 
  };
  </script>
  
  <style scoped>
  .facility-card {
    width: 30%;
    border: 1px solid #ddd;
    padding: 10px;
    border-radius: 5px;
    background-color: #f9f9f9;
  }
  button {
    padding: 5px 10px;
    border: none;
    background-color: #007bff;
    color: white;
    cursor: pointer;
  }
  button:hover {
    background-color: #0056b3;
  }
  </style>
  