<template>
  <div>  
    <form action="/">
      <van-search
        v-model="search_text"
        @search="onSearch"
      />
    </form>
    <van-tabs swipeable sticky>
      <van-tab title="首页">
        <van-swipe :autoplay="2000" lazy-render>
          <van-swipe-item v-for="swipe in swipes" :key="swipe">
            <img :src="swipe" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
          </van-swipe-item>
        </van-swipe>

        <van-grid :column-num="2" border="false">
          <van-grid-item border="false">
            <van-image :src="require('@/assets/item/1/item.jpg')" />
            <div class="van-multi-ellipsis--l2" style="font-size: smaller;">
              <van-tag type="primary">偶像</van-tag>
              过气偶像，一斤两块过气偶像，一斤两块过气偶像，一斤两块过气偶像，一斤两块过气偶像，一斤两块过气偶像，一斤两块过气偶像，一斤两块
            </div>
          </van-grid-item>
        </van-grid>

      </van-tab>
      <van-tab title="书籍">
        内容
      </van-tab>
      <van-tab title="音乐">
        内容 3
      </van-tab>
      <van-tab title="动画">
        内容 4
      </van-tab>
    </van-tabs>
    <BottomNav/>
  </div>
</template>

<script>
import swipe1 from '@/assets/images/swipe1.jpeg'
import swipe2 from '@/assets/images/swipe2.jpeg'
import swipe3 from '@/assets/images/swipe3.jpeg'
import swipe4 from '@/assets/images/swipe4.jpeg'
import BottomNav from '@/components/BottomNav.vue'
import axios from 'axios'
import Qs from 'qs'
export default {
  components: {
    BottomNav
  },
  data() {  
    return {
      search_text: '',
      swipes: [
        swipe1,
        swipe2,
        swipe3,
        swipe4,     
      ]
    }
  },
  mounted() {
    let isLogin = sessionStorage.getItem("isLogin")
    if(isLogin != null){
      this.loadProfile()
    }
  },
  methods: {
    loadProfile: function () {
      let uid = sessionStorage.getItem("uid")
      axios({
        url: '/api/user/' + uid + ':'+ uid +'',
        method: 'post',
      }).then(res =>{
        let info = res.data.returnObject
        sessionStorage.setItem("username", info.userName)
        sessionStorage.setItem("useremail", info.userEmail)
        sessionStorage.setItem("followers", info.followerCount)
        sessionStorage.setItem("following", info.followingCount)
        sessionStorage.setItem("money", info.userCurrency)
        sessionStorage.setItem("point", info.userPoint)
        sessionStorage.setItem("coupon", info.couponCount)
        sessionStorage.setItem("birthday", info.userBirthday)
        sessionStorage.setItem("hasActivated", info.userHasActivated)
        sessionStorage.setItem("isMerchant", info.userIsMerchant)
        sessionStorage.setItem("introduction", info.userIntroduction)
        sessionStorage.setItem("sex", info.userSex)
        sessionStorage.setItem("state", info.userState)
      })
    }
  }
}
</script>
