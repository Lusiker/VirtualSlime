<template>
  <div class="grayBG">
    <form action="/">
      <van-search
        v-model="search_text"
        show-action
      >
        <template #action>
          <div @click="search">搜索</div>
        </template>
      </van-search>
    </form>
    <van-tabs swipeable sticky>
      <van-tab title="首页">
        <van-swipe :autoplay="2000" lazy-render>
          <van-swipe-item v-for="swipe in swipes" :key="swipe">
            <img :src="swipe" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
          </van-swipe-item>
        </van-swipe>
        <van-list
            :loading="home.loading"
            :finished="home.finished"
            finished-text="没有更多了"
            @load="onLoad1"
        >
          <div class="van-clearfix">
          <div style="margin: 1%; width: 48%; float: left; background-color: white; border-radius: 8px; overflow: hidden;" v-for="item in home.items" >
            <router-link :to="'/item/' + item.iid">
              <van-image
                  :src="require('@/assets/item/' + item.iid + '/pic.jpg')"
                  :show-loading=false
              />
              <div class="van-ellipsis" style="font-size: var(--van-font-size-md);">
                <van-tag plain type="primary" style="margin-left: 2%;">{{ item.type }}</van-tag>
                {{ item.name }}
              </div>
              <div style="float: left; color: #FB7299; font-size: var(--van-line-height-sm); margin-top: 2%; margin-left: 4%;">
                ¥{{ item.price }}
              </div>
              <div style="float: right; color: var(--van-gray-5); font-size: var(--van-line-height-xs); margin-right: 4%">
                {{ item.rate }}
              </div>
            </router-link>
          </div>
          </div>
        </van-list>
      </van-tab>
      <van-tab title="图书">
        <van-list
            :loading="book.loading"
            :finished="book.finished"
            finished-text="没有更多了"
            @load="onLoad2"
        >
          <div class="van-clearfix">
            <div style="margin: 1%; width: 48%; float: left; background-color: white; border-radius: 8px; overflow: hidden;" v-for="item in book.items" >
              <router-link :to="'/item/' + item.iid">
                <van-image
                    :src="require('@/assets/item/' + item.iid + '/pic.jpg')"
                    :show-loading=false
                />
                <div class="van-ellipsis" style="font-size: var(--van-font-size-md);">
                  <van-tag plain type="primary" style="margin-left: 2%;">{{ item.type }}</van-tag>
                  {{ item.name }}
                </div>
                <div style="float: left; color: #FB7299; font-size: var(--van-line-height-sm); margin-top: 2%; margin-left: 4%;">
                  ¥{{ item.price }}
                </div>
                <div style="float: right; color: var(--van-gray-5); font-size: var(--van-line-height-xs); margin-right: 4%">
                  {{ item.rate }}
                </div>
              </router-link>
            </div>
          </div>
        </van-list>
      </van-tab>
      <van-tab title="游戏">
        <van-list
            :loading="game.loading"
            :finished="game.finished"
            finished-text="没有更多了"
            @load="onLoad3"
        >
          <div class="van-clearfix">
            <div style="margin: 1%; width: 48%; float: left; background-color: white; border-radius: 8px; overflow: hidden;" v-for="item in game.items" >
              <router-link :to="'/item/' + item.iid">
                <van-image
                    :src="require('@/assets/item/' + item.iid + '/pic.jpg')"
                    :show-loading=false
                />
                <div class="van-ellipsis" style="font-size: var(--van-font-size-md);">
                  <van-tag plain type="primary" style="margin-left: 2%;">{{ item.type }}</van-tag>
                  {{ item.name }}
                </div>
                <div style="float: left; color: #FB7299; font-size: var(--van-line-height-sm); margin-top: 2%; margin-left: 4%;">
                  ¥{{ item.price }}
                </div>
                <div style="float: right; color: var(--van-gray-5); font-size: var(--van-line-height-xs); margin-right: 4%">
                  {{ item.rate }}
                </div>
              </router-link>
            </div>
          </div>
        </van-list>
      </van-tab>
      <van-tab title="音乐">
        <van-list
            :loading="music.loading"
            :finished="music.finished"
            finished-text="没有更多了"
            @load="onLoad4"
        >
          <div class="van-clearfix">
            <div style="margin: 1%; width: 48%; float: left; background-color: white; border-radius: 8px; overflow: hidden;" v-for="item in music.items" >
              <router-link :to="'/item/' + item.iid">
                <van-image
                    :src="require('@/assets/item/' + item.iid + '/pic.jpg')"
                    :show-loading=false
                />
                <div class="van-ellipsis" style="font-size: var(--van-font-size-md);">
                  <van-tag plain type="primary" style="margin-left: 2%;">{{ item.type }}</van-tag>
                  {{ item.name }}
                </div>
                <div style="float: left; color: #FB7299; font-size: var(--van-line-height-sm); margin-top: 2%; margin-left: 4%;">
                  ¥{{ item.price }}
                </div>
                <div style="float: right; color: var(--van-gray-5); font-size: var(--van-line-height-xs); margin-right: 4%">
                  {{ item.rate }}
                </div>
              </router-link>
            </div>
          </div>
        </van-list>
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
      ],
      home: {
        loading: false,
        finished: false,
        page: 1,
        items: [ ]
      },
      book: {
        loading: false,
        finished: false,
        page: 1,
        items: [ ]
      },
      game: {
        loading: false,
        finished: false,
        page: 1,
        items: [ ]
      },
      music: {
        loading: false,
        finished: false,
        page: 1,
        items: [ ]
      },
      types: [
          '热卖', '新品', '推荐', '排名', '评分较高', '今日特卖'
      ]
    }
  },
  mounted() {
    if(sessionStorage.getItem("isLogin") === 'true'){
      this.loadProfile()
    }
  },
  // beforeCreate () {
  //   document.querySelector('body').setAttribute('style', 'background-color: var(--van-gray-2);')
  // },
  // beforeRouteLeave() {
  //   document.querySelector('body').removeAttribute('style')
  // },
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
        sessionStorage.setItem("lastLogin", info.lastLoginString)
        sessionStorage.setItem("isAvatarChanged", info.userHasChangedAvatar)
      })
    },
    search: function () {
      this.$router.push('/search/' + this.search_text)
    },
    onLoad1: function () {
      this.home.loading = true
      // console.log('加载呢！')
      axios({
        url: '/api/home',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          page: this.home.page
        }
      }).then(res =>{
        if(res.data.stateEnum.state === 1) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.home.items.push({
              type: this.types[Math.round(Math.random() * 5)],
              iid: item.iid,
              name: item.itemName,
              price: item.itemPrice,
              rate: item.rating
            })
          }
          this.home.page += 1
          this.home.loading = false
        } else {
          this.home.loading = false
          this.home.finished = true
        }
      })
    },
    onLoad2: function () {
      this.book.loading = true
      // console.log('加载呢！')
      axios({
        url: '/api/home/cid=6',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          page: this.book.page
        }
      }).then(res =>{
        if(res.data.stateEnum.state === 1) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.book.items.push({
              type: this.types[Math.round(Math.random() * 5)],
              iid: item.iid,
              name: item.itemName,
              price: item.itemPrice,
              rate: item.rating
            })
          }
          this.book.page += 1
          this.book.loading = false
        } else {
          this.book.loading = false
          this.book.finished = true
        }
      })
    },
    onLoad3: function () {
      this.game.loading = true
      // console.log('加载呢！')
      axios({
        url: '/api/home/cid=2',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          page: this.game.page
        }
      }).then(res =>{
        if(res.data.stateEnum.state === 1) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.game.items.push({
              type: this.types[Math.round(Math.random() * 5)],
              iid: item.iid,
              name: item.itemName,
              price: item.itemPrice,
              rate: item.rating
            })
          }
          this.game.page += 1
          this.game.loading = false
        } else {
          this.game.loading = false
          this.game.finished = true
        }
      })
    },
    onLoad4: function () {
      this.music.loading = true
      // console.log('加载呢！')
      axios({
        url: '/api/home/cid=4',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          page: this.music.page
        }
      }).then(res =>{
        if(res.data.stateEnum.state === 1) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.music.items.push({
              type: this.types[Math.round(Math.random() * 5)],
              iid: item.iid,
              name: item.itemName,
              price: item.itemPrice,
              rate: item.rating
            })
          }
          this.music.page += 1
          this.music.loading = false
        } else {
          this.music.loading = false
          this.music.finished = true
        }
      })
    }
  }
}
</script>

<style>
  a:link,
  a:visited{
    color: #000;
    text-decoration: none;
  }
  .grayBG {
    background-color: var(--van-gray-2);
  }
</style>