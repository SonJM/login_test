<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/>
</template>

<script>
export default {

}
</script>

<style>
body{
  padding: 10px;
  margin: 10px;
}
</style>