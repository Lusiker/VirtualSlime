<template>
  <van-tabbar route active-color="#FB7299">
    <van-tabbar-item replace to="/" icon="shop-collect-o">
      首页
    </van-tabbar-item>
    <van-tabbar-item replace to="/discover" icon="like-o">
      发现
    </van-tabbar-item>
    <van-tabbar-item replace to="/chat" icon="chat-o">
      消息
    </van-tabbar-item>
    <van-tabbar-item replace to="/cart" icon="cart-o">
      购物车
    </van-tabbar-item>
    <van-tabbar-item replace :to="isLogin === true ? '/user' : '/login'" icon="manager-o">
      我的
    </van-tabbar-item>
  </van-tabbar>
</template>

<script>
export default {
  data() {
    return {
      isLogin: this.$store.state.userState.isLogin,
      myUid: this.$store.state.userState.myUid,
    }
  },
  watch:{
    '$store.state.userState.isLogin': function(){ 
      this.isLogin = this.$store.state.userState.isLogin
    },
    '$store.state.userState.myUid': function(){ 
      this.myUid = this.$store.state.userState.myUid
    } 
  }
}
</script>

<style>

</style>