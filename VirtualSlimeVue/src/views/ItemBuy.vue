<template>
  <div>
    <van-nav-bar
        left-arrow
        title="购买商品"
        @click-left="onClickLeft"
    />
    <van-swipe-cell>
      <van-card
          :price="item.price"
          :desc="item.brief"
          :title="item.name"
          class="goods-card"
          :thumb="require('@/assets/item/' + item.iid + '/pic.jpg')"
      />
    </van-swipe-cell>
    <van-submit-bar :price="Number(item.priceString)" button-text="提交订单" @submit="submitShow = true" button-color="#FB7299"/>
    <van-popup v-model:show="submitShow" position="bottom" :style="{ height: '30%' }" round>
      <div style="margin-top: 10%">
        <van-cell-group inset :title="'当前余额' + money">
          <van-cell value="确认要购买此商品吗？" />
          <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
            <van-button type="primary" block @click="buyItem">确认</van-button>
          </div>
        </van-cell-group>
      </div>
    </van-popup>
  </div>
</template>

<script>
import { Notify } from 'vant';
import axios from 'axios'
export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      submitShow: false,
      uid: sessionStorage.getItem("uid"),
      money: sessionStorage.getItem('money'),
      item: {
        iid: this.$route.params.iid,
        name: '',
        price: '',
        priceString: '',
        brief: '',
      }
    }
  },
  mounted() {
    this.loadItem()
  },
  methods: {
    loadItem: function () {
      this.item.iid = this.$route.params.iid
      axios({
        url: '/api/item/' + this.item.iid,
        method: 'post',
      }).then(res =>{
        let reItem = res.data.returnObject
        this.item.name = reItem.itemName
        this.item.price = reItem.itemPrice
        // console.log(reItem.itemPrice)
        // console.log(this.item.price)
        // console.log(this.item.price.toString().replace('.','') + '0')
        let pr = this.item.price.toString()
        if(pr.includes('.')) {
          let p = this.item.price.toString().split('.')
          let p1 = p[0]
          let p2 = p[1]
          if (p2.length < 2) {
            p2 = p2 + '0'
          }
          this.item.priceString = p1 + p2
        } else {
          this.item.priceString = pr + '00'
        }
        // console.log(this.item.priceString)
        this.item.brief = reItem.itemBrief
      })
    },
    buyItem: function () {
      axios({
        url: '/api/item/' + this.item.iid + '/buy?uid=' + sessionStorage.getItem('uid'),
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 4) {
          sessionStorage.setItem("money", res.data.returnObject)
          this.money = res.data.returnObject
          Notify({type: 'primary', message: '购买成功'})
          this.submitShow = false
          // this.$router.push('/item/' + this.item.iid)
        } else if(res.data.stateEnum.state === -2){
          Notify({type: 'primary', message: '你已经购买过了'})
          this.submitShow = false
        } else if(res.data.stateEnum.state === -4) {
          Notify({type: 'primary', message: '你钱不够'})
          this.submitShow = false
        }
      })
    }
  }
}
</script>

<style>
.goods-card {
  margin: 0;
  background-color: white;
}
</style>