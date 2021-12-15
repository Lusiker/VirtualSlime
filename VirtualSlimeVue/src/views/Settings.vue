<template>
  <div>
    <van-nav-bar
        title="注册"
        left-arrow
        @click-left="onClickLeft"
    />

  </div>
</template>

<script>
import axios from 'axios'
import Qs from 'qs'
import { Notify } from 'vant';
import loginRegisterPic from '@/assets/images/swipe3.jpeg'
export default {
  data() {
    return {

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
            useremail: this.useremail,
            password: this.password1
          }
        }).then(res =>{


        })
      }
    }
  }
}
</script>

<style scoped>

</style>
