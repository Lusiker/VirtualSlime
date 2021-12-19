<template>
  <div>
    <van-nav-bar
        left-arrow
        title="已购商品"
        @click-left="onClickLeft"
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
        <van-button square text="下载" type="success" class="delete-button" @click="deleteItem(item.iid)"/>
      </template>
    </van-swipe-cell>
  </div>
</template>

<script>
import { Notify } from 'vant';
import axios from 'axios'
export default {
  data() {
    return {
      onClickLeft: () => history.back(),
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
    deleteItem: function () {
      Notify({type: 'primary', message: '下载成功'})
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