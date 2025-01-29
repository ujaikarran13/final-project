<template>
  <div>
    <header class="view-header">
      <h2>User Profile</h2>
    </header>
    <loading-spinner v-if="isUserLoading" />
    <div v-else id="view-content">
      <user-profile v-bind:user="user" />
      <h3>Facilities</h3>
      <loading-spinner v-if="isTableLoading" />
      <table-facilities v-else  
          v-bind:facilities="facilities" v-bind:hideUser="true"
          v-on:refresh:facilities="getFacilities" />
    </div>
  </div>
</template>

<script>
import AuthService from '../services/AuthService';
import FacilityService from '../services/FacilityService';
import FacilityTable from '../components/FacilityTable.vue';
import LoadingSpinner from '../components/LoadingSpinner.vue';
import UserProfile from '../components/UserProfile.vue';

export default {
  components: { LoadingSpinner, UserProfile },
  data() {
    return {
      isUserLoading: true,
      isTableLoading: true,
      userId: this.$route.params.userId,
      facilities: [],
      user: {}
    }
  },
  created() {
    this.getUserProfile();
    this.getFacilities();
  },
  methods: {
    getUserProfile() {
      this.isUserLoading=true;
      AuthService.getUserProfile(this.userId)
          .then(response => {
            this.user = response.data;
            this.isUserLoading = false;
          })
          .catch(error => {
            const response = error.response;
            if (response.status === 401) {
              // Token expired
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Session timeout. Please login again.'});
              this.$store.commit("LOGOUT");
              this.$router.push({name: "login"});
            } else {
              // Something else unexpected happened
              this.isUserLoading = false;
              let response = error.response;
              console.error(`Could not get user profile for user id ${this.userId}.`, response.message);
              this.$store.commit('ADD_NOTIFICATION', {type: 'error', message: 'Sorry, something unexpected occurred. Please try again later.'});
            }
          })
    },
    getFacilities() {
      this.isTableLoading = true;
      FacilityService.getFacilities(this.userId)
          .then(response => {
            this.facilities = response.data
            this.isTableLoading = false;
          })
          .catch(error => {
            this.isTableLoading=false;
            let response = error.response;
            if (response.status === 401) {
              // Token expired
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Session timeout. Please login again.'});
              this.$store.commit("LOGOUT");
              this.$router.push({name: "login"});
            } else {
              // Something unexpected happened
              this.$store.commit("ADD_NOTIFICATION", {type: 'error', message: 'Sorry, something unexpected occurred. Please try again later.'});
              let response = error.response;
              console.error(`Getting user's (id=${this.user.id}) facilities was unsuccessful: `, response.message);
            }
      });
    }
  }
}
</script>

<style>
#user-profile {
  display: flex;
  align-items: center;
  gap: 2rem;
  border: 1px solid rgba(136, 193, 231, 0.7);
  border-radius: 0.5rem;
  margin-bottom: 2rem;
  padding: 1.5rem;
  max-width: 50rem;
}
.user-name {
  font-size: 1.5rem;
  margin-bottom: 1rem;
}
.img-profile {
  font-size: 120px;
  max-width: 120px;
  border-radius: 50%;
}
</style>