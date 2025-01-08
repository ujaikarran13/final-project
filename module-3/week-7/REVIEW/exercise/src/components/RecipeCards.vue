<template>
  <section id="recipe-cards">
    <article class="recipe-card" v-for="recipe in recipes" v-bind:key="recipe.recipeId">
      <div class="recipe-image">
        <img class="cover" v-bind:src="recipe.imageName" />
      </div>
      <div class="name">{{ recipe.name }}</div>
    </article>
  </section>
</template>

<script>
export default {
  props: ['recipes'],
};
</script>

<style scoped>
#recipe-cards {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.recipe-card {
  display: inline-block;
  width: 325px;
  height: 410px;
  margin: 30px;
  text-align: center;
  font-size: 1.32rem;
  font-weight: bold;
  color: #1d3557;
  box-shadow: 0 4px 7px #1d3557;
  overflow: hidden;
}

.cover {
  object-fit: cover;
  width: 100%;
}
</style>