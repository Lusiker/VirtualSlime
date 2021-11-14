<template>
  <div>
    <HelloWorld msg="Welcome to Virtual Slime Home page"/>
    <Login/>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import Login from '@/components/login.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld,
    Login
  }
}
</script>
