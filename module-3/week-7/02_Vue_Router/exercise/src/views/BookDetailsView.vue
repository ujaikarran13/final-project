<template>
    <div v-if="book">
      <BookDetails :book="book" />
    </div>
    <div v-else>
      <p>Loading...</p>
    </div>
  </template>
  
  <script>
  import BookDetails from '../components/BookDetails.vue';
  
  export default {
    components: {
      BookDetails
    },
    data() {
      return {
        book: null
      };
    },
    created() {
      const isbn = this.$route.params.isbn; 
      this.loadBook(isbn);
    },
    methods: {
      loadBook(isbn) {
        const book = this.$store.state.books.find(book => book.isbn === isbn);
        if (book) {
          this.book = book;
        }
      }
    }
  };
  </script>
  
  <style scoped>
  
  </style>
  