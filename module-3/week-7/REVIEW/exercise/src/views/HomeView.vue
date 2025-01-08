<template>
  <div id="home">
    <section id="search">
      <!-- Add search-box here -->
    </section>
    <article id="about-us">
      <h2>Who are we?</h2>
      <p>We at Culinary Explosion! like to think of ourselves as a collection of like-minded individuals.
        <br><br> We take interesting recipes from around the web and reference them here to be
        shared, tested, and tasted!
      </p>
    </article>
  </div>
</template>

<script>

export default {
  components: {

  },
}
</script>

<style scoped>
h1,
h2,
p {
  color: #1d3557;
  margin: 0;
}

#home {
  display: grid;
  gap: 40px;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "search search search"
    ". about-us . ";
}

article {
  grid-area: about-us;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-content: center;
  height: 200px;
  margin-bottom: 100px;
}

article p {
  font-size: 1.16rem;
  margin-bottom: 22px;
}

#search {
  grid-area: search;
  height: 700px;
  background-image: url('../assets/exploded-kiwi.jpg');
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  opacity: 96%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>