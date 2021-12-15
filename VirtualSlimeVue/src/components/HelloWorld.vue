<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <van-button type="primary">主要按钮</van-button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<style scoped>

</style>
