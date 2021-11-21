<template>
  <el-container>
    <el-header style="--el-header-padding: 0px, 0px;">
      <Header/>
    </el-header>
    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
import Header from '@/components/Header.vue'
export default {
  components: {
    Header
  }
}
</script>

<style>
html, body, #app {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
}
</style>