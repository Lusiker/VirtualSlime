<template>
  <div>
    <van-nav-bar
        left-arrow
        @click-left="onClickLeft"
    />
    <van-image width="100" height="100" :src="require('@/assets/item/' + item.iid + '/pic.jpg')" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
    <div style="color: #FB7299; font-size: 25px; margin-top: 2%; margin-left: 4%;">
      ¥{{ item.price }}
    </div>
    <div class="van-ellipsis" style="font-size: var(--van-line-height-md); margin-top: 2%; margin-left: 4%;">
      {{ item.name }}
    </div>
    <van-action-bar>
      <van-action-bar-icon icon="chat-o" text="客服" />
      <van-action-bar-icon icon="cart-o" text="购物车" to="/cart"/>
      <van-action-bar-icon icon="star" text="已收藏" color="#FB7299" />
      <van-action-bar-button type="warning" text="加入购物车" color="#faacc2" @click="putCart" />
      <van-action-bar-button type="danger" text="立即购买" color="#FB7299"/>
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
      item: {
        iid: 0,
        name: '',
        price: ''
      }
    }
  },
  mounted() {
    // console.log(this.$route.params.iid)
    this.item.iid = this.$route.params.iid
    axios({
      url: '/api/item/' + this.item.iid,
      method: 'post',
    }).then(res =>{
      let reItem = res.data.returnObject
      this.item.name = reItem.itemName
      this.item.price = reItem.itemPrice
    })
  },
  methods: {
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
    }
  }
}
</script>

<style scoped>

</style>