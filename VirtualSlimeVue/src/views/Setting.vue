<template>
  <div>
    <van-nav-bar
        title="账号资料"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-cell-group style="margin-top: 5%">
      <van-cell title="头像" value="内容" />
      <van-cell title="昵称" :value="info.name" />
      <van-cell title="性别" :value="info.sex" />
      <van-cell title="出生年月" :value="info.birthday" />
      <van-cell title="个性签名" :value="info.introduction" />
    </van-cell-group>
    <van-cell-group style="margin-top: 5%">
      <van-cell title="UID" :value="info.uid" />
      <van-cell title="商家认证" :value="info.isMerchant" />
      <van-cell title="账号状态" :value="info.state" />
    </van-cell-group>
  </div>
</template>

<script>
import axios from 'axios'
import Qs from 'qs'
export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      info: {
        uid: 0,
        name: sessionStorage.getItem("username"),
        sex: 0,
        birthday: 0,
        introduction: '---',
        isMerchant: false,
        state: '---'
      }
    }
  },
  methods: {
    register: function() {
      axios({
        url: '/api/register',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {

        }
      }).then(res =>{


      })
    }
  }
}
</script>

<style scoped>

</style>
