<template>
    <header id="app-header">
        <div id="app-info">
          <img class="app-logo" src="img/calendarlogo.png" alt="calendarlogo"/>
          <h1>{{title}}</h1>
        </div>
        <div id="user-info" v-if="user.id">
          <div id="user-logout" >
            <div class="user-name">{{user.displayName ? user.displayName : user.username}}</div>
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
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
  }
  
  .user-name {
    margin-bottom: 0.25rem;
    font-size: 1.2rem;
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
  </style>