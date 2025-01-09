<template>
  <div id="recipes-view">
    <h1 id="recipes-title">Recipes</h1>
    <!-- Add recipe-cards here -->
  </div>
</template>

<script>
import recipeService from '../services/RecipeService';

export default {
  components: {

  },
  computed: {
    recipeData() {
      // Get the search term from the query string
      const searchTerm = this.$route.query.searchTerm;
      // TODO - If searching, filter recipes
      return recipeService.getRecipes();
    }
  }
};
</script>

<style scoped>
#recipes-title {
  text-align: center;
}
</style>