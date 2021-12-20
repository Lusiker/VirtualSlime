<template>
  <div>
    <van-nav-bar
        left-arrow
        title="我发布的商品"
        @click-left="onClickLeft"
    />

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
    this.loadMyItem()
  },
  methods: {
    loadMyItem: function () {
      axios({
        url: '/api/user/' + this.uid + '/items',
        method: 'post',
      }).then(res => {
        if (res.data.stateEnum.state === 8) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.items.push({
                iid: item.iid,
                name: item.itemName,
                brief: item.itemBrief,
                price: item.itemPrice
              })
          }
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