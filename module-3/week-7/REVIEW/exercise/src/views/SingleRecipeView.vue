<template>
  <div id="single-recipe-view">
    <div id="recipe-details">
      <!-- Add recipe-details here -->
    </div>
    <div id="reviews-section">
      <!-- Add reviews-section here -->
    </div>
  </div>
</template>

<script>
import recipeService from '../services/RecipeService';

export default {
  components: {},

  data() {
    return {
      recipe: {},
    };
  },

  created() {
    this.recipe = recipeService.getRecipe([this.$route.params.id]);
  },
};
</script>

<style scoped>
#reviews-title {
  font-size: 2rem;
  font-weight: 500;
  padding-left: 10px;
}
</style>