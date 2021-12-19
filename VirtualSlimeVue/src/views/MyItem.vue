<template>
  <div>
    <van-nav-bar
        title="我发布的商品"
    />
    <van-swipe-cell v-for="item in items">
      <van-card
          :price="item.price"
          :desc="item.brief"
          :title="item.name"
          class="goods-card"
          :thumb="require('@/assets/item/' + item.iid + '/pic.jpg')"
      />
    </van-swipe-cell>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
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
        url: '/api/user/' + this.uid + '/bought',
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