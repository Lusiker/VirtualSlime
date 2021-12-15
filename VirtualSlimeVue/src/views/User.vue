<template>
  <div>
    <!-- <van-image width="100" height="100" :src="userPic" style="display: inline-block; width: 100%; max-width: 100%; height: auto;position: absolute;"/> -->
    <van-row gutter="25" style="position: relative; padding: 25% 20px 10px 20px">
      <van-col span="6">
        <van-image
          round
          width="5rem"
          height="5rem"
          :src="require('@/assets/user/' + userInfo.uid + '/avator.jpg')"
        />
      </van-col>
      <van-col span="18" style="padding-top: 3%;">
        <div class="van-ellipsis" style="font-size: 120%;color: #FB7299">
          {{ userInfo.name }}
        </div>
        <div class="van-ellipsis" style="margin-top: 5%;color: var(--van-grid-item-text-color)">
          <b>{{ userInfo.followers }}</b>&nbsp;粉丝
          &nbsp;&nbsp;
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
      <van-grid-item icon="orders-o" icon-color="#1989fa" text="我的订单" to="/"/>
      <van-grid-item icon="clock-o" icon-color="#1989fa" text="历史记录" to="/"/>
      <van-grid-item icon="star-o" icon-color="#1989fa" text="我的收藏" to="/"/>
      <van-grid-item icon="balance-list-o" icon-color="#1989fa" text="已购商品" to="/"/>
    </van-grid>
    <van-cell value="相关部门"/>
    <van-grid>
      <van-grid-item icon="shop-o" icon-color="#ff8917" text="我的发布" to="/"/>
      <van-grid-item icon="paid" icon-color="#ff8917" text="我的钱包" to="/"/>
      <van-grid-item icon="balance-o" icon-color="#ff8917" text="积分兑换" to="/"/>
      <van-grid-item icon="balance-list-o" icon-color="#ff8917" text="不知道了" to="/"/>
    </van-grid>
    <van-cell value="更多服务"/>
    <van-cell-group>
      <van-cell title="&nbsp;&nbsp;联系客服" icon="service-o" is-link to="/"/>
      <van-cell title="&nbsp;&nbsp;设置" icon="setting-o" is-link to="/"/>
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
        uid: 1,
        name: '大東亜共栄圏帝国海軍',
        followers: 1140,
        following: 5140,
        money: '648.55',
        point: 3280,
        coupon: 35
      } 
    }
  }
}
</script>

<style>

</style>