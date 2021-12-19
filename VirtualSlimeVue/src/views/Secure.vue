<template>
  <div>
    <van-nav-bar
        title="安全中心"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-image width="100" height="100" :src="require('@/assets/images/secureBanner.jpg')" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
    <van-cell-group>
      <van-cell title="修改邮箱" :value="info.email" is-link @click="updateEmailShow = true"/>
      <van-popup v-model:show="updateEmailShow" position="bottom" :style="{ height: '30%' }" round>
        <div style="margin-top: 10%">
          <van-cell-group inset title="更换邮箱后，需要重新激活哦~">
            <van-field v-model="update.email" label="修改邮箱" placeholder="请输入新邮箱" />
            <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
              <van-button type="primary" block @click="updateEmailConfirm">保存</van-button>
            </div>
          </van-cell-group>
        </div>
      </van-popup>

      <van-cell title="修改密码" is-link @click="updatePwdShow = true"/>
      <van-popup v-model:show="updatePwdShow" position="bottom" :style="{ height: '40%' }" round>
        <van-cell-group inset style="margin-top: 10%">
          <van-field v-model="update.oldPwd" label="旧密码" placeholder="请输入旧密码" type="password"/>
          <van-field v-model="update.newPwd1" label="新密码" placeholder="请输入新密码" type="password"/>
          <van-field v-model="update.newPwd2" label="重复新密码" placeholder="请重复新密码" type="password"/>
          <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
            <van-button type="primary" block @click="updatePwdConfirm">保存</van-button>
          </div>
        </van-cell-group>
      </van-popup>
    </van-cell-group>

    <van-cell-group style="margin-top: 10%">
      <van-cell title="上次登录" :value="info.lastLogin" />
      <van-cell title="退出登录" is-link @click="logout"/>
    </van-cell-group>
  </div>
</template>

<script>
import axios from 'axios'
import { Notify } from 'vant';
import Qs from "qs";
export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      updateEmailShow: false,
      updatePwdShow: false,
      info: {
        uid: sessionStorage.getItem("uid"),
        email: sessionStorage.getItem("useremail"),
        lastLogin: sessionStorage.getItem("lastLogin")
      },
      update: {
        email: '',
        oldPwd: '',
        newPwd1: '',
        newPwd2: ''
      }
    }
  },
  methods: {
    updateEmailConfirm: function () {
      this.updateEmailShow = false
      axios({
        url: '/api/user/' + this.info.uid + '/update/email=' + this.update.email,
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 2) {
          sessionStorage.setItem("useremail", res.data.returnObject)
          sessionStorage.setItem("state", "RESTRICTED")
          this.info.email = sessionStorage.getItem("useremail")
          Notify({type: 'primary', message: '修改成功'})
        }
      })
    },
    updatePwdConfirm: function () {
      this.updatePwdShow = false
      if(this.update.oldPwd === '') {
        Notify({type: 'primary', message: '请输入旧密码'})
      } else if(this.update.newPwd1 === '') {
        Notify({type: 'primary', message: '请输入新密码'})
      } else if(this.update.newPwd2 === '') {
        Notify({type: 'primary', message: '请重复新密码'})
      } else if(this.update.newPwd1 !== this.update.newPwd2){
        Notify({type: 'primary', message: '密码不一致'})
      } else {
        axios({
          url: '/api/user/' + this.info.uid + '/update/password',
          method: 'post',
          transformRequest: [function (data) {
            return Qs.stringify(data)
          }],
          data: {
            oldPassword: this.update.oldPwd,
            newPassword: this.update.newPwd1
          }
        }).then(res => {
          let resState = res.data.stateEnum.state
          if(resState === -5) {
            Notify({type: 'primary', message: '密码太简单了，不可以'})
          } else if(resState === -1) {
            Notify({type: 'primary', message: '密码错误'})
          } else if(resState === 2) {
            Notify({type: 'primary', message: '修改成功'})
          }
        })
      }
    },
    logout: function () {
      sessionStorage.clear()
      this.$store.state.userState.isLogin = false
      this.$store.state.userState.myUid = null
      Notify({type: 'primary', message: '退出成功'})
      this.$router.push("/")
    }
  }
}
</script>

<style scoped>

</style>
