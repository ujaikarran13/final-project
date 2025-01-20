<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <header class="flex">
      <h1>Topics</h1>
    </header>
    <topic-list v-bind:topics="topics"/>
  </div>
</template>

<script>
import TopicList from '../components/TopicList.vue';
import TopicService from '../services/TopicService';

export default {
  components: {
    TopicList
  },
  data() {
    return {
      topics: [],
      isLoading: true
    };
  },
  methods: {
    async fetchTopics() {
      try {
        const topics = await TopicService.list();
        this.topics = topics;
      } catch (error) {
        console.error('Failed to load topics:', error);
      } finally {
        this.isLoading = false;
      }
    }
  },
  created() {
    this.fetchTopics();
  }
};
</script>

<style scoped>
</style>