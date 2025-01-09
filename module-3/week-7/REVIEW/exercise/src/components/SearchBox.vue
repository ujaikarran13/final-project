<template>
  <div>
    <h1>Find a Recipe</h1>
    <div id="search-box">
      <button class="icon-button" id="search-button" v-on:click="searchRecipes()" tabindex="-1">
        <i class="fa-solid fa-magnifying-glass fa-3x"></i>
      </button>
      <input type="text" name="search-tb" id="search-tb" v-on:keyup.enter="searchRecipes()" v-model="searchString" />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    delay: {
      type: Number,
      default: 1000,
    },
  },
  data() {
    return {
      searchString: "",
    };
  },
  methods: {
    searchRecipes() {
      this.$router.push({ name: 'recipes', query: { searchTerm: this.searchString } });
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 2.5em;
  color: #f1faee;
  margin: 0;
}

#search-box {
  display: inline-block;
  background-color: #f1faee;
  opacity: .8;
}

#search-tb {
  font-size: 3em;
  border: none;
  color: #1d3557;
}

#search-tb:focus-visible {
  outline: none;
}

#search-button {
  color: #1d3557;
  cursor: pointer;
  background-color: transparent;
  border: none;
}
</style>
