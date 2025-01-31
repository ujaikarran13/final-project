<template>
  <div id="scheduling-app">
    <app-header id="app-header" title="Scheduling Application" />
    <main-nav id="main-nav" />
    <main id="main-content">
      <router-view />
    </main>
    <app-footer id="app-footer"/>
    </div>
</template>

<script>
import AppHeader from './components/AppHeader.vue';
import AppFooter from './components/AppFooter.vue';
import MainNav from './components/MainNav.vue';

export default {
  components: {AppHeader, AppFooter, MainNav}
}

</script>
<style scoped>
#app-header {
  background-color: hsl(137, 100%, 78%);
  color: rgb(5, 4, 4);
  padding: 20px;
  text-align: center;
  font-size: 2rem;
  font-weight: bold; 
}

#main-nav {
  background-color:  hsl(137, 100%, 78%);
  padding: 10px;
}

#main-content {
  padding: 20px;
}

#app-footer {
  background-color: hsl(137, 100%, 78%);
  color: rgb(6, 5, 5);
  padding: 10px;
  text-align: center;
}
</style>