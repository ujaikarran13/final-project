<template>
  <header>
      Welcome to Scheduling, to view doctors offices, please sign in.
    </header>
  <div id="login">
    
    <form v-on:submit.prevent="login">
      <h1>Please Sign In</h1>
      <div id="fields">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <div><button type="submit">Sign in</button></div>
      </div>
      <hr/>
      Need an account? <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
#login {
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
  background-color: rgb(192, 245, 192);
  border-radius: 8px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 1.5em;
  color: #333;
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Dancing Script', cursive;
}

header {
  font-size: 1.5em;
  color: #000000;
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 100;
  font-style: oblique;
}

#fields {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

label {
  font-size: 1.1em;
  color: #333;
  margin-bottom: 5px;
  font-family: 'Dancing Script', cursive;
}

input {
  padding: 10px;
  font-size: 1em;
  border: 1px solid #a4cd9e;
  border-radius: 5px;
  margin-bottom: 15px;
  width: 100%;
  box-sizing: border-box;
}

button {
  padding: 12px;
  font-size: 1.1em;
  font-family: 'Dancing Script', cursive;
  background-color: #74cd80;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
}

button:hover {
  background-color: #36c74b;
}
</style>
