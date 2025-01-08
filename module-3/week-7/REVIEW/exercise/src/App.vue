<template>
  <div id="recipe-app">
    <header>
      <h1>Culinary explosion!</h1>
      <!-- Insert nav here -->
    </header>
    <main>
      <router-view />
    </main>
    <AppMessage v-if="$store.state.message.length" />
  </div>
</template>

<script>
import AppMessage from './components/AppMessage.vue';

export default {
  components: {
    AppMessage
  }
}
</script>

<style>
#recipe-app {
  display: flex;
  flex-direction: column;
}

header {
  background-color: #f1faee;
  color: #e63946;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}

nav {
  font-size: 1.16rem;
  background-color: #e63946;
  color: #f1faee;
}

nav a {
  text-decoration: none;
  color: #f1faee;
  padding: 0 13px;
}

nav a:hover {
  text-decoration: none;
  color: #e63946;
  background-color: #f1faee;
  padding: 0 13px;
  box-shadow: 0 0 5px #1d3557;
}

header h1 {
  text-align: center;
}

main {
  overflow: scroll;
}
</style>
