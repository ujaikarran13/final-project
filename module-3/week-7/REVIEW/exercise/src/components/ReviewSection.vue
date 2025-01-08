<template>
  <div id="review-section">
    <div id="leave-review-box">
      <h3 id="header">Did you enjoy {{ this.recipe.name }}?</h3>
      <form v-on:submit.prevent="submitReview">
        <div id="review-info">
          <textarea
            name="body"
            id="body"
            v-model="newReview.body"
            placeholder="Let us know here!"
          ></textarea>
          <label for="stars">Stars:</label>
          <input
            type="number"
            id="stars"
            name="stars"
            min="0"
            max="5"
            v-model="newReview.stars"
          />
          <label for="name">Display Name:</label>
          <input
            type="text"
            id="name"
            v-model="newReview.user"
            placeholder="Anon, M"
          />
          <label for="location">Where are you from?</label>
          <input
            type="text"
            id="location"
            v-model="newReview.location"
            placeholder="Place, USA"
          />
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
    <div id="review-cards">
      <!-- Insert review-cards here -->
    </div>
  </div>
</template>

<script>
import reviewService from '../services/ReviewService';

export default {
  components: {},
  props: ['recipe'],
  data() {
    return {
      nextId: 0,
      currentRecipeId: 0,
      newReview: {
        recipeId: 0,
        user: "",
        location: "",
        body: "",
        date: "",
        stars: 0,
      },
      reviews: [],
    };
  },
  methods: {
    submitReview() {
      // create new review object
      let nextId = this.reviews.length + 1;
      const newReview = {
        ...this.newReview,
        recipeId: this.currentRecipeId,
        id: nextId,
      };
      this.reviews.unshift(newReview);

      // clear the form
      this.newReview = {
        recipeId: 0,
        user: "",
        location: "",
        body: "",
        date: "",
        stars: 0,
      };
    }
  },
  created() {
    this.reviews = reviewService.getRecipeReviews(this.recipe.recipeId);
    this.currentRecipeId = this.recipe.recipeId;
  },
};
</script>

<style scoped>
button {
  font-size: 1.1em;
  display: inline-block;
  padding: 10px 20px;
  margin-right: 8px;
  text-align: center;
  text-decoration: none;
  color: #f1faee;
  background-color: #457b9d;
  border: 0;
  outline: none;
}

button:hover {
  background-color: #a8dadc;
}

#review-section {
  margin: 0 auto 50px auto;
  width: 60%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 10px;
}

#review-section,
button {
  width: 50%;
  align-self: center;
}

#leave-review-box {
  padding-left: 20px;
  color: #f1faee;
  background-color: #1d3557;
}

#review-info {
  width: 60%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 10px;
}

textarea {
  height: 108px;
}

textarea,
input {
  font-size: 1rem;
  margin: 10px 0;
}

input {
  height: 27px;
}

input[type="number"] {
  width: 50px;
}

#review-cards {
  padding: 10px 0;
}
</style>