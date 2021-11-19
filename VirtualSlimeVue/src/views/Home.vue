<template>
  <div>
    <HelloWorld msg="Welcome to VS!"/>
    <RegisterLogin/>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import RegisterLogin from '@/components/RegisterLogin.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld,
    RegisterLogin
  }
}
</script>
