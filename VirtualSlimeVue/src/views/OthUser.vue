<template>
  <div>
    <van-nav-bar
        left-arrow
        title="用户详情"
        @click-left="onClickLeft"
    />
    <van-row gutter="25" style="position: relative; padding: 5% 20px 10px 20px">
      <van-col span="6">
        <van-image
            round
            width="5rem"
            height="5rem"
            :src="require('@/assets/user/' + userInfo.uid + '/avatar.jpg')"
        />
      </van-col>
      <van-col span="18" style="padding-top: 3%;">
        <div class="van-ellipsis" style="font-size: 120%;color: #FB7299">
          {{ userInfo.name }}
          <van-button type="primary" size="small" style="float: right" @click="follow" :plain="isFollowBoolean === true" v-if="curUid !== userInfo.uid">
            {{ isFollow }}
          </van-button>
        </div>
        <div class="van-ellipsis" style="color: var(--van-grid-item-text-color)">
          <b>{{ userInfo.followers }}</b>&nbsp;粉丝&nbsp;&nbsp;
          <b>{{ userInfo.following }}</b>&nbsp;关注
        </div>
      </van-col>
    </van-row>
    <van-cell value="发布的商品"/>
    <van-swipe-cell v-for="item in items">
      <router-link :to="'/item/' + item.iid">
        <van-card
            :price="item.price"
            :desc="item.brief"
            :title="item.name"
            class="goods-card"
            :thumb="require('@/assets/item/' + item.iid + '/pic.jpg')"
        />
      </router-link>
    </van-swipe-cell>
  </div>
</template>

<script>
import axios from "axios";
import { Notify } from 'vant';
export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      curUid: sessionStorage.getItem("uid"),
      userInfo: {
        uid: this.$route.params.uid,
        name: '',
        followers: '',
        following: '',
      },
      items: [],
      isFollowBoolean: '',
      isFollow: ''
    }
  },
  mounted() {
    this.loadProfile()
    this.loadItem()
  },
  methods: {
    loadProfile: function () {
      axios({
        url: '/api/user/' + this.userInfo.uid + ':'+ this.curUid +'',
        method: 'post',
      }).then(res =>{
        let info = res.data.returnObject
        this.userInfo.name = info.userName
        this.userInfo.followers = info.followerCount
        this.userInfo.following = info.followingCount
        this.isFollowBoolean = info.hasFollowed
        this.isFollow = info.hasFollowed === true ? '取关' : '关注'
      })
    },
    loadItem: function () {
      axios({
        url: '/api/user/' + this.curUid + '/items',
        method: 'post',
      }).then(res => {
        if (res.data.stateEnum.state === 8) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.items.push({
              iid: item.iid,
              name: item.itemName,
              brief: item.itemBrief,
              price: item.itemPrice,
            })
          }
        }
      })
    },
    follow: function () {
      axios({
        url: '/api/user/' + this.userInfo.uid + ':'+ this.curUid +'/follow',
        method: 'post',
      }).then(res =>{
        let state = res.data.returnObject
        if(state === 'unfollow') {
          this.isFollowBoolean = false
          this.isFollow = '关注'
          Notify({type: 'primary', message: '取关成功'})
        } else if(state === 'follow') {
          this.isFollowBoolean = true
          this.isFollow = '取关'
          Notify({type: 'primary', message: '关注成功'})
        }
      })
    }
  }
}
</script>

