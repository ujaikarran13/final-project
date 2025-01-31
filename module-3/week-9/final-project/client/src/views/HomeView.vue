<template>
  <div class="home">
    <div id="heading-line">
      <h1>
        Home
        <loading-spinner id="spinner" v-bind:spin="true" />
      </h1>
    </div>
    <div class="centered-heading">
      <h2>Welcome to Scheduling</h2>
    </div>
    <p>
      <img class="homepage-photo" src="img/medicaloffice.png" alt="Doctors Office Photo" />
    </p>
    <input type="checkbox" name="loading" id="loading" v-model="isLoading" /> Is
    Loading
    <p id="login-message" v-if="!isLoggedIn">
      Welcome! You may browse anonymously as much as you wish,<br />
      but you must
      <router-link v-bind:to="{ name: 'login' }">Login</router-link> to view
      doctors facilities.
    </p>
    <section class="Info-section">
    <h2>Accepted Insurance Providers</h2>
    <ul class="insurance-list">
        <li>Aetna</li>
        <li>United Healthcare</li>
        <li>Blue Cross Blue Shield</li>
        <li>Medicare</li>
        </ul>
  </section>

  <section class="Info-section">
      <h2>Accepted Insurance Providers</h2>
      <ul class="payment-options">
        <li>Credit Card (We accept Mastercard and VISA)</li>
        <li>Debit Card</li>
        <li>Health Savings Account (HSA)</li>
        <li>Cash</li>
      </ul>
    </section>
    
    <font-awesome-icon
      v-bind:class="{ 'view-icon': true, active: cardView }"
      v-on:click="cardView = true"
      icon="fa-solid fa-grip"
      title="View tiles"
    />
    <font-awesome-icon
      v-bind:class="{ 'view-icon': true, active: !cardView }"
      v-on:click="cardView = false"
      icon="fa-solid fa-table"
      title="View table"
    />

    
  </div>
</template>

<script>
import LoadingSpinner from "../components/LoadingSpinner.vue";


export default {
  components: {
    LoadingSpinner, 
  },
  data() {
    return {
      isLoading: false,
      cardView: true,
      shieldView: true,
    };
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.token.length > 0;
    },
  },
};
</script>

<style scoped>
#spinner {
  color: green;
}

.view-icon {
  font-size: 1.2rem;
  margin-right: 7px;
  padding: 3px;
  color: #444;
  border-radius: 3px;
}

.view-icon.active {
  background-color: lightgreen;
}

.view-icon:not(.active) {
  font-size: 1.2rem;
  margin-right: 7px;
  cursor: pointer;
}

.view-icon:not(.active):hover {
  color: blue;
  background-color: rgba(255, 255, 255, 0.7);
}

.info-section {
  margin-bottom: 30px;
}

h2 {
  font-size: 1.5em;
  color: #048715;
  margin-bottom: 10px;
  font-family: 'Dancing Script', cursive;
}

ul li {
  background: #f4f4f4;
  margin: 5px 0;
  padding: 8px;
  border-radius: 5px;
  font-size: 1.1em;
}

ul li:hover{
  background-color: rgb(190, 238, 188);
}

.insurance-list li {
 background-color: #d9f7be;
}

.payment-options li {
background-color: #8ed0ff;
}
.payment-options li:hover {
background-color: #f7f7f7;
}

.homepage-photo {
 size: 100%;
}

.centered-heading {
  text-align: center;
  margin-top: 20px;
  border: 2px solid #048715;
  background-color: #d9f7be;
}

</style>