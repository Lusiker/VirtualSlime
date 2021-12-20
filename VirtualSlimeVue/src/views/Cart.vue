<template>
  <div>
    <van-nav-bar
        title="购物车"
    />
    <van-swipe-cell v-for="item in items">
      <van-card
          :price="item.price"
          :desc="item.brief"
          :title="item.name"
          class="goods-card"
          :thumb="require('@/assets/item/' + item.iid + '/pic.jpg')"
      />
      <template #right>
        <van-button square text="删除" type="danger" class="delete-button" @click="deleteItem(item.iid)"/>
      </template>
    </van-swipe-cell>
    <BottomNav/>
  </div>
</template>

<script>
import BottomNav from '@/components/BottomNav.vue'
import axios from 'axios'
import Qs from "qs";
export default {
  components: { 
    BottomNav 
  },
  data() {
    return {
      uid: sessionStorage.getItem("uid"),
      items: []
    }
  },
  mounted() {
    this.loadCart()
  },
  methods: {
    loadCart: function () {
      axios({
        url: '/api/user/' + this.uid + '/cart',
        method: 'post',
      }).then(res => {
        if (res.data.stateEnum.state === 3) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.items.push({
                  iid: item.iid,
                  name: item.itemName,
                  brief: item.itemBrief,
                  price: item.itemPrice
                }
            )
          }
        }
      })
    },
    deleteItem: function (iid) {
      axios({
        url: '/api/user/' + this.uid + '/cart/remove',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          iid: iid
        }
      }).then(res => {
        this.items = []
        this.loadCart()
        // this.$router.go(0)
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

.delete-button {
  height: 100%;
}
</style>