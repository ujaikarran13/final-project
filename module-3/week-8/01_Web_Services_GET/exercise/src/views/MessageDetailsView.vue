<template>
  <div class="loading" v-if="isLoading">
    <p>Loading...</p>
  </div>
  <div v-else>
    <nav>
      <router-link v-bind:to="{ name: 'TopicDetailsView', params: { topicId: topicId } }">Back to Topic Details</router-link>
    </nav>
    <message-details v-bind:message="message" />
  </div>
</template>

<script>
import MessageDetails from '../components/MessageDetails.vue';
import MessageService from '../services/MessageService';

export default {
  components: {
    MessageDetails,
  },
  data(){
    return {
     
      message: null,
      isLoading: true
    }
  },
  methods: {
    async fetchMessageDetails() {
      try {
        const messageId = this.$route.params.messageId;
        this.message = await MessageService.get(messageId);
      } catch (error) {
        console.error('Failed to load message details:', error);
      } finally {
        this.isLoading = false;
      }
    }
  },
  created() {
    this.fetchMessageDetails();
  }
};
</script>

<style scoped>
</style>
