<template>
<div class="card" :class="{ read: book.read }">
    <img
      :src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'"
      alt="Book cover"
      class="book-image"
    />
    <h2 :class="book-title">{{ book.title }}</h2>
    <h3 :class="book-author">{{ book.author }}</h3>
    <button
      v-bind:class="book.read ? 'mark-unread' : 'mark-read'"
      @click="toggleReadStatus"
    >
      {{ book.read ? 'Mark as Unread' : 'Mark as Read' }}
    </button>
  </div>
</template>

<script>
export default {
 props: {
  book: Object
 },
   
  
  methods: {
    toggleReadStatus() {
      this.$store.commit('toggleReadStatus', this.book.isbn);
    }
  }
}
</script>

<style>
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 250px;
  height: 550px;
  margin: 20px;
  position: relative;
}

.book-card.read {
  background-color: lightgray;
}

.book-title {
  font-size: 1.5rem;
}

.book-author {
  font-size: 1rem;
}

.book-image {
  width: 80%;
}

.mark-read, .mark-unread {
  display: block;
  position: absolute;
  bottom: 40px;
  width: 80%;
  left: 10%;
  font-size: 1rem;
}
</style>