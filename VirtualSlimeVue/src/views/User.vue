<template>
  <div>
    <van-row gutter="25" style="position: relative; padding: 25% 20px 10px 20px">
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
        </div>
        <div class="van-ellipsis" style="margin-top: 5%;color: var(--van-grid-item-text-color)">
          <b>{{ userInfo.followers }}</b>&nbsp;粉丝&nbsp;&nbsp;
          <b>{{ userInfo.following }}</b>&nbsp;关注
        </div>
      </van-col>
    </van-row>
    <van-grid :column-num="3" style="margin-left: 7%;margin-right: 7%;">
      <van-grid-item icon="gold-coin-o" :text="'余额：¥' + userInfo.money"/>
      <van-grid-item icon="points" :text="'积分：' + userInfo.point" />
      <van-grid-item icon="coupon-o" :text="'优惠券：' + userInfo.coupon + '张'" />
    </van-grid>
    <van-grid>
      <van-grid-item icon="orders-o" icon-color="#1989fa" text="我的评价" to="/"/>
      <van-grid-item icon="clock-o" icon-color="#1989fa" text="历史记录" to="/"/>
      <van-grid-item icon="star-o" icon-color="#1989fa" text="我的收藏" to="/"/>
      <van-grid-item icon="balance-list-o" icon-color="#1989fa" text="已购商品" to="/"/>
    </van-grid>
    <van-cell value="购买相关"/>
    <van-grid>
      <van-grid-item icon="shop-o" icon-color="#ff8917" text="我的发布" to="/"/>
      <van-grid-item icon="paid" icon-color="#ff8917" text="我的钱包" to="/pay"/>
      <van-grid-item icon="balance-o" icon-color="#ff8917" text="积分兑换" to="/"/>
      <van-grid-item icon="balance-list-o" icon-color="#ff8917" text="我的订单" to="/"/>
    </van-grid>
    <van-cell value="更多服务"/>
    <van-cell-group>
      <van-cell title="&nbsp;&nbsp;联系客服" icon="service-o" is-link to="/"/>
      <van-cell title="&nbsp;&nbsp;账号资料" icon="setting-o" is-link to="/setting"/>
      <van-cell title="&nbsp;&nbsp;安全中心" icon="shield-o" is-link to="/secure"/>
    </van-cell-group>
    <BottomNav/>
  </div>
</template>

<script>
import BottomNav from '@/components/BottomNav.vue'
export default {
  components: { 
    BottomNav
  },
  data() {
    return {
      userInfo: {
        uid: sessionStorage.getItem("uid"),
        name: sessionStorage.getItem("username"),
        followers: sessionStorage.getItem("followers"),
        following: sessionStorage.getItem("following"),
        money: sessionStorage.getItem("money"),
        point: sessionStorage.getItem("point"),
        coupon: sessionStorage.getItem("coupon")
      } 
    }
  },
  methods: {

  }
}
</script>

