<template>
    <header id="app-header">
        <div id="app-info">
          <img class="app-logo" src="img/calendarlogo.png" alt="calendarlogo"/>
          <h1>{{title}}</h1>
        </div>
        <div id="user-info" v-if="user.id">
          <div id="user-logout" >
            <div class="user-name">{{user.displayName ? user.displayName : user.username}}</div>
            <div class="login-link" ><router-link v-bind:to="{ name: 'login' }">Login</router-link></div>
            <div class="login-link" ><router-link v-bind:to="{ name: 'logout' }">Logout</router-link></div>
          </div>
            <img v-if="user.profileImageUrl" class="img-profile" v-bind:src="user.profileImageUrl" alt="profile image" />
            <font-awesome-icon v-else class="img-profile" icon="fa-solid fa-user" />
        </div>
      </header>
  </template>
  
  <script>
  export default {
    props: ['title'],
    computed: {
      user() {
        return this.$store.state.user
      }
    }
  }
  </script>
  
  <style scoped>
  #app-header {
    display: grid;
    grid-template-columns: 3fr 50px minmax(150px, 1fr);
    grid-template-rows: 40px;
    background-color: rgb(136, 231, 150);
    padding: 5px;
    border: 2px solid rgb(0, 117, 41);
    border-radius: 5px;
  }
  
  #app-info {
    grid-column: 1 / 2;
    display: flex;
    align-items: center;
  }
  
  #user-info {
    display: flex;
    gap: 1rem;
    grid-column: 3 / 4;
    align-items: center;
    justify-content: flex-end;
    text-align: center;
  }
  
  #user-logout {
    display: flex;
    flex-flow: column nowrap;
    justify-content: center;
    font-style: italic;
   
  }
  
  .user-name {
    margin-bottom: 0.25rem;
    font-size: 1.2rem;
    font-style: oblique;
  }
  .app-logo{
  
    align-items: flex-start;
    height: 100px;
    padding: 10px;
  }
  .img-profile {
    height: 40px;
    margin-right: 1rem;
    border-radius: 50%;
  }
  .login-link {
    font-size: 1.1rem;
  }
  .login-link a {
  text-decoration: none; 
  padding: 8px 12px;
  border-radius: 5px;
  color: #333; 
  transition: background-color 0.3s ease, color 0.3s ease;
  }
  .login-link a:hover{
  background-color: rgb(3, 214, 203);
  color: #000000;
  }
 
  
  </style>