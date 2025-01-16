<template>
  <div class="reading-list">
    <h1>My Reading List</h1>
    <ul>
      <li v-for="book in $store.state.books" :key="book.isbn"> 
        <BookCard :book="book" />
      </li>
    </ul>
  </div>
</template>

<script>
import BookCard from './BookCard.vue';

export default {
  components: {
    BookCard
  }
}
</script>

<style>
.reading-list {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>