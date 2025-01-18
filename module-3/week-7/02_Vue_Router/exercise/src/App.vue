<template>
  <div id="book-app">
    <nav>
      <router-link v-bind:to="{name: 'home'}">Home View</router-link> |
      <router-link v-bind:to="{name: 'myBooks'}">My Books View</router-link> |
      <router-link v-bind:to="{name: 'addBook'}">My Books View</router-link>
    </nav>
    <router-view />
  </div>
</template>

<script>

export default {
  name: 'App'
};
</script>

<style>
#book-app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 60px;
}
</style>
