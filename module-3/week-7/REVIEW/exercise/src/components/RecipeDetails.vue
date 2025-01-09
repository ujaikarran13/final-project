<template>
  <div id="single-recipe-info">
    <div id="ingredients-panel">
      <h1 class="name">{{ recipe.name }}</h1>
      <h2>Ingredients</h2>
      <ul>
        <li v-for="ingredient in recipe.ingredients" v-bind:key="ingredient.id">
          {{ ingredient.text }}
        </li>
      </ul>
    </div>
    <div id="button-bar">
      <button type="button" v-on:click="goToRecipePage">See Full Recipe</button>
      <button type="button">
        Add to My Favorites
      </button>
    </div>
    <div class="recipe-image">
      <img v-bind:src="recipe.imageName" class="cover" />
    </div>
  </div>
</template>

<script>
export default {
  props: ['recipe'],
  methods: {
    goToRecipePage() {
      window.open(this.recipe.instructionsUrl, "_blank");
    },
  }
};
</script>

<style scoped>
#single-recipe-info {
  box-shadow: 0 5px 5px #1d3557;
  display: grid;
  grid-template-columns: 0.5fr 1fr 1fr;
  grid-template-areas:
    ". ingredients image"
    ". ingredients image"
    ". buttons image";
}

#button-bar {
  grid-area: buttons;
  height: 55px;
  display: flex;
  flex-direction: row;
}

button {
  font-size: 1.1em;
  display: inline-block;
  padding: 10px 20px;
  margin-right: 8px;
  text-align: center;
  text-decoration: none;
  color: #f1faee;
  background-color: #1d3557;
  border: 0;
  outline: none;
}

button:hover {
  background-color: #457b9d;
}

#ingredients-panel {
  height: 100%;
  grid-area: ingredients;
}

.recipe-image {
  grid-area: image;
}

.name {
  grid-area: title;
  font-size: 3rem;
  font-weight: 400;
}

.cover {
  max-width: 100%;
  height: 100%;
}
</style>