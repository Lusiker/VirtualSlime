<template>
  <div>
    <van-nav-bar
        left-arrow
        title="商品详情"
        @click-left="onClickLeft"
    />
    <van-image width="100" height="100" :src="require('@/assets/item/' + item.iid + '/pic.jpg')" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
      <div style="color: #FB7299; font-size: 26px; margin-top: 2%; margin-left: 4%;">
        ¥{{ item.price }}
        <div style="float: right; font-size: 15px; margin-right: 4%;">
          评分：{{ item.rating }}
        </div>
      </div>
    <div class="van-hairline--top"></div>
    <router-link :to="'/otheruser/' + item.uid">
    <van-image
        round
        width="3rem"
        height="3rem"
        style="float: right; margin: 3%"
        :src="require('@/assets/user/' + item.uid + '/avatar.jpg')"
    />
    </router-link>
<!--    <div style="float: right; margin-top: 5%; color: #FB7299; font-size: 20px;">{{ item.userName }}</div>-->
    <div class="van-ellipsis" style="font-size: 20px; margin-top: 2%; margin-left: 4%;">
      {{ item.name }}
    </div>
    <div class="van-ellipsis" style="font-size: 15px; margin-top: 2%; margin-left: 4%; color: var(--van-gray-6)">
      {{ item.brief }}
    </div>
    <div class="van-hairline--bottom" style="margin-top: 4%"></div>
    <van-cell-group>
      <van-cell is-link arrow-direction="down" title="发一条评价吧~" value="发送评价" @click="sendComShow = true" />
      <van-popup v-model:show="sendComShow" position="bottom" :style="{ height: '40%' }" round>
        <van-cell-group inset style="margin-top: 10%">
          <van-field
              v-model="comment.content"
              label="输入评价"
              rows="3"
              type="textarea"
              left-icon="comment-o"
              maxlength="50"
              show-word-limit
          />
          <van-rate
              v-model="comment.rating"
              :size="25"
              color="#ffd21e"
              void-icon="star"
              void-color="#eee"
              style="margin-left: 4%; margin-top: 4%;"
          />
          <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
            <van-button type="primary" block @click="sendCom">发送</van-button>
          </div>
        </van-cell-group>
      </van-popup>
    </van-cell-group>

    <van-cell-group inset v-for='com in item.comments'>
      <van-cell icon="user-circle-o" :title="com.userName" :value="'评分：' + com.rating" :label="com.content" />
    </van-cell-group>
    <van-share-sheet
        v-model:show="showShare"
        title="立即分享给好友"
        :options="options"
    />
    <van-action-bar>
      <van-action-bar-icon icon="chat-o" text="客服" />
      <van-action-bar-icon icon="cart-o" text="购物车" to="/cart"/>
      <van-action-bar-icon icon="share-o" text="分享" @click="showShare = true"/>
      <van-action-bar-button type="warning" text="加入购物车" color="#faacc2" @click="putCart" />
      <van-action-bar-button type="danger" text="立即购买" color="#FB7299" :to="'/item/' + item.iid + '/buy'"/>
    </van-action-bar>
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
      showShare: false,
      sendComShow: false,
      comRating: '',
      comment: {
        uid: sessionStorage.getItem("uid"),
        content: '',
        rating: ''
      },
      options: [
        [
          { name: '微信', icon: 'wechat' },
          { name: '朋友圈', icon: 'wechat-moments' },
          { name: '微博', icon: 'weibo' },
          { name: 'QQ', icon: 'qq' },
        ],
        [
          { name: '复制链接', icon: 'link' },
          { name: '分享海报', icon: 'poster' },
          { name: '二维码', icon: 'qrcode' },
          { name: '小程序码', icon: 'weapp-qrcode' },
        ],
      ],
      item: {
        uid: 1,
        userName: '',
        iid: 0,
        name: '',
        price: '',
        brief: '',
        rating: '',
        comments: []
      }
    }
  },
  mounted() {
    this.loadItem()
  },
  methods: {
    loadItem: function () {
      this.item.comments = []
      this.item.iid = this.$route.params.iid
      axios({
        url: '/api/item/' + this.item.iid,
        method: 'post',
      }).then(res =>{
        let reItem = res.data.returnObject
        this.item.name = reItem.itemName
        this.item.price = reItem.itemPrice
        this.item.uid = reItem.uid
        this.item.userName = reItem.userName
        this.item.brief = reItem.itemBrief
        this.item.rating = reItem.ratingSting
        this.item.comments = reItem.comments
      })
    },
    putCart: function () {
      axios({
        url: '/api/item/' + this.item.iid + '/toCart',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          uid: sessionStorage.getItem("uid")
        }
      }).then(res =>{  //-2 3
        let re = res.data.stateEnum.state
        if(re === -2) {
          Notify({type: 'primary', message: '已经在购物车里'})
        } else if (re === 3) {
          Notify({type: 'primary', message: '添加成功'})
        }
      })
    },
    sendCom: function () {
      axios({
        url: '/api/item/' + this.item.iid + '/addComment',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: this.comment
      }).then(res =>{  //-2 3
        let re = res.data.stateEnum.state
        if(re === -2) {
          Notify({type: 'primary', message: '已经评价过了'})
        } else if (re === 1) {
          Notify({type: 'primary', message: '评价成功'})
          this.sendComShow = false
        }
      })
      this.loadItem()
    }
  }
}
</script>