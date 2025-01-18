<template>
    <div class="book-details">
      <h2>{{ book.title }}</h2>
      <p><strong>Author:</strong> {{ book.author }}</p>
      <p><strong>ISBN:</strong> {{ book.isbn }}</p>
      <p><strong>Number of Pages:</strong> {{ book.numPages }}</p>
      <p>
        <strong>More Info:</strong>
        <a :href="book.moreInfoLink" target="_blank" rel="noopener noreferrer">Click here</a>
      </p>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      book: Object 
    }
  };
  </script>
  
  <style scoped>
  .book-details {
    margin: 20px;
    font-size: 1rem;
  }
  
  .book-details a {
    color: #007bff;
  }
  
  .book-details a:hover {
    text-decoration: underline;
  }
  </style>
  