<template>
  <div>
    <van-nav-bar
        left-arrow
        title="搜索结果"
        @click-left="onClickLeft"
    />
    <van-list
        :loading="loading"
        :finished="finished"
        finished-text="没有更多了"
        @load="loadSearchItem"
    >
      <div class="van-clearfix">
        <div style="margin: 1%; width: 48%; float: left; background-color: white; border-radius: 8px; overflow: hidden;" v-for="item in items" >
          <router-link :to="'/item/' + item.iid">
            <van-image
                :src="require('@/assets/item/' + item.iid + '/pic.jpg')"
                :show-loading=false
            />
            <div class="van-ellipsis" style="font-size: var(--van-font-size-md);">
              <van-tag plain type="primary" style="margin-left: 2%;">{{ item.type }}</van-tag>
              {{ item.name }}
            </div>
            <div style="float: left; color: #FB7299; font-size: var(--van-line-height-sm); margin-top: 2%; margin-left: 4%;">
              ¥{{ item.price }}
            </div>
            <div style="float: right; color: var(--van-gray-5); font-size: var(--van-line-height-xs); margin-right: 4%">
              {{ item.rate }}
            </div>
          </router-link>
        </div>
      </div>
    </van-list>

  </div>
</template>

<script>
import axios from 'axios'
import Qs from 'qs'
export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      uid: sessionStorage.getItem("uid"),
      context: '',
      page: 1,
      loading: false,
      finished: false,
      items: [],
      types: [
        '热卖', '新品', '推荐', '排名靠前', '评分较高', '今日特卖'
      ]
    }
  },
  methods: {
    loadSearchItem: function () {
      this.context = this.$route.params.context
      axios({
        url: '/api/search/item',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          keyword: this.context,
          page: this.page
        }
      }).then(res =>{
        if(res.data.stateEnum.state === 1) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.items.push({
              type: this.types[Math.round(Math.random() * 5)],
              iid: item.iid,
              name: item.itemName,
              price: item.itemPrice,
              rate: item.rating
            })
          }
          console.log(this.items)
          this.page += 1
          this.loading = false
        } else {
          this.loading = false
          this.finished = true
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