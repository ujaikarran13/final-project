<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <nav>
      <router-link v-bind:to="{ name: 'HomeView' }">Back to Topic List</router-link>
    </nav>
    <topic-details v-bind:topic="topic" />
    <div v-if="messages.length">
        <message-list :messages="messages" />
      </div>
  </div>
</template>

<script>
import TopicDetails from '../components/TopicDetails.vue';
import TopicService from '../services/TopicService';
import MessageService from '../services/MessageService';
import MessageList from '../components/MessageDetails.vue';

export default {
  components: {
    TopicDetails,
    MessageList
  },
  data() {
    return {
      topic: null,
      messages: [],
      isLoading: true
    };
  },
  methods: {
    async fetchTopicDetails() {
      
      try {
        const topicId = this.$route.params.topicId;
        this.topic = await TopicService.get(topicId);
        this.messages = await MessageService.get(topicId); // Assuming the same topicId is used for messages
      } catch (error) {
        console.error('Failed to load topic details or messages:', error);
      } finally {
        this.isLoading = false;
      }
    }
  },
  created() {
    this.fetchTopicDetails();
  }
};
</script>

<style scoped>
</style>