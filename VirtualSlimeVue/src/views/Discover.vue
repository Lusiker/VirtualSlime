<template>
  <div>
        <van-list
            :loading="home.loading"
            :finished="home.finished"
            finished-text="没有更多了"
            @load="onLoad1"
        >
          <div class="van-clearfix">
            <div style="margin: 1%; width: 48%; float: left; background-color: white; border-radius: 8px; overflow: hidden;" v-for="item in home.items" >
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
    <BottomNav/>
  </div>
</template>

<script>
import swipe1 from '@/assets/images/swipe1.jpeg'
import swipe2 from '@/assets/images/swipe2.jpeg'
import swipe3 from '@/assets/images/swipe3.jpeg'
import swipe4 from '@/assets/images/swipe4.jpeg'
import BottomNav from '@/components/BottomNav.vue'
import axios from 'axios'
import Qs from 'qs'
export default {
  components: {
    BottomNav
  },
  data() {
    return {
      search_text: '',
      swipes: [
        swipe1,
        swipe2,
        swipe3,
        swipe4,
      ],
      home: {
        loading: false,
        finished: false,
        page: 1,
        items: [ ]
      },
      book: {
        loading: false,
        finished: false,
        page: 1,
        items: [ ]
      },
      types: [
        '热卖', '新品', '推荐', '排名', '评分较高', '今日特卖'
      ]
    }
  },
  beforeCreate () {
    document.querySelector('body').setAttribute('style', 'background-color: var(--van-gray-2);')
  },
  beforeRouteLeave() {
    document.querySelector('body').removeAttribute('style')
  },
  methods: {
    onLoad1: function () {
      this.home.loading = true
      // console.log('加载呢！')
      axios({
        url: '/api/home',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          page: this.home.page
        }
      }).then(res =>{
        if(res.data.stateEnum.state === 1) {
          // console.log(res.data.returnObject)
          for (var item of res.data.returnObject) {
            this.home.items.push({
              type: this.types[Math.round(Math.random() * 5)],
              iid: item.iid,
              name: item.itemName,
              price: item.itemPrice,
              rate: item.rating
            })
          }
          this.home.page += 1
          this.home.loading = false
        } else {
          this.home.loading = false
          this.home.finished = true
        }
      })
    },
  }
}
</script>

<style>
a:link,
a:visited{
  color: #000;
  text-decoration: none;
}
</style>