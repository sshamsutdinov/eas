<template>
  <div id="app">
     
     <Header/>
     <router-view/>
    <Sidebar/>
  </div>
</template>

<script>

import Header from '@/components/Header.vue';
import Sidebar from '@/components/Sidebar.vue';

export default {
  name: 'App',
  components: {
    Header,
    Sidebar
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
