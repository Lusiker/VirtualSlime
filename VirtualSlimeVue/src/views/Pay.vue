<template>
  <div>
    <van-nav-bar
        title="我的钱包"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-image width="100" height="100" :src="require('@/assets/images/payBanner.jpg')" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
    <van-grid :column-num="3" style="margin-top: 10%; margin-left: 7%; margin-right: 7%;">
      <van-grid-item icon="gold-coin-o" :text="'余额：¥' + userInfo.money"/>
      <van-grid-item icon="points" :text="'积分：' + userInfo.point" />
      <van-grid-item icon="coupon-o" :text="'优惠券：' + userInfo.coupon + '张'" />
    </van-grid>
    <van-cell value="充值" icon="cash-back-record" size="large" is-link arrow-direction="down"/>
    <van-radio-group v-model="checked" >
      <van-cell-group >
        <van-cell title="微信" clickable @click="payShow = true" icon="wechat-pay" style="color: #00c250" size="large">
          <template #right-icon>
            <van-radio name="1" />
          </template>
        </van-cell>
        <van-cell title="支付宝" clickable @click="payShow = true" icon="alipay" style="color: #1677ff" size="large">
          <template #right-icon>
            <van-radio name="2" />
          </template>
        </van-cell>
      </van-cell-group>
    </van-radio-group>
    <van-popup v-model:show="payShow" position="bottom" :style="{ height: '30%' }" round>
      <van-cell-group inset style="margin-top: 10%">
        <van-field v-model="payCount" label="金额" placeholder="请输入金额" />
        <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
          <van-button type="primary" block @click="payConfirm">保存</van-button>
        </div>
      </van-cell-group>
    </van-popup>
  </div>
</template>

<script>
import axios from "axios";
import {Notify} from "vant";

export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      checked: false,
      payShow: false,
      payCount: '',
      userInfo: {
        uid: sessionStorage.getItem("uid"),
        name: sessionStorage.getItem("username"),
        money: sessionStorage.getItem("money"),
        point: sessionStorage.getItem("point"),
        coupon: sessionStorage.getItem("coupon")
      }
    }
  },
  methods: {
    payConfirm: function () {
      axios({
        url: '/api/user/' + this.userInfo.uid + '/update/addCurrency=' + this.payCount,
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 2) {
          sessionStorage.setItem("money", res.data.returnObject)
          this.userInfo.money = sessionStorage.getItem("money")
          Notify({type: 'primary', message: '充值成功'})
          this.payShow = false
        } else if(res.data.stateEnum.state === -1) {
          Notify({type: 'primary', message: '充值失败'})
        }
      })
    }
  }
}
</script>

<style scoped>

</style>