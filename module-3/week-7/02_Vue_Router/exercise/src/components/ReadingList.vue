<template>
  <div class="reading-list">
    <router-link v-for="book in books" :key="book.isbn" :to="'/book/' + book.isbn">
      <book-card :book="book" />
    </router-link>
  </div>
</template>

<script>
import BookCard from '../components/BookCard.vue';

export default {
  components: {
    BookCard
  },
  computed: {
    books() {
      return this.$store.state.books;
    }
  }
};


</script>

<style>
.book-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>