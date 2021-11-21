<template>
  <div>
    <HelloWorld msg="这是首页"/>
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
