<template>
  <div id="recipe-app">
    <header>
      <h1>SomeRecipes Inc.</h1>
    </header>
    <section id="recipe">
      <RecipeDetail />
    </section>
    <section id="nutrition">
    </section>
    <section id="rating">
    </section>
  </div>
</template>

<script>
import RecipeDetail from './components/RecipeDetail.vue';

export default {
  components: {
    RecipeDetail,
},
}
</script>

<style>
body {
  background-color: #264653;

}

p {
  font-size: 1.17em;
  font-weight: normal;
  margin: 5px;
}

a {
  cursor: pointer;
}

section {
  background-color: #2a9d8f;
  margin: 8px;
  border-radius: 6px;
}

#recipe-app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #fff;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "header header header header"
    ". recipe recipe ."
    ". nutrition nutrition ."
    ". rating rating .";
}

header {
  grid-area: header;
  background-color: #e76f51;
  box-shadow: 0 0 5px black;
}

#details,
#rating,
#nutrition {
  box-shadow: 0 0 5px #222;
}

nav {
  grid-area: nav;
}

#nutrition {
  grid-area: nutrition;
}

#recipe {
  grid-area: recipe;
}

#rating {
  grid-area: rating;
}

.title-btn {
  display: inline-block;
  padding: 10px 20px;
  margin-right: 8px;
  text-align: center;
  text-decoration: none;
  color: #ffffff;
  background-color: #e76f51;
  border-radius: 6px;
  border: 0;
  outline: none;
}

.title-btn:disabled {
  background-color: #ed947e;
}

.title-btn:hover {
  background-color: #e35937;
}
</style>
