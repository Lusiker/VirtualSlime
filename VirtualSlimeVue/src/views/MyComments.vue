<template>
  <div>
    <van-nav-bar
        left-arrow
        title="我的评价"
        @click-left="onClickLeft"
    />

    <van-cell-group inset v-for='com in comments'>
      <router-link :to="'/item/' + com.iid">
      <van-cell :title="'评价 ' + com.item + ' ：' + com.content" :value="'评分：' + com.rating" :label="'发送于：' + com.createdAtString" />
      </router-link>
    </van-cell-group>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      uid: sessionStorage.getItem("uid"),
      comments: []
    }
  },
  mounted() {
    this.loadMyComments()
  },
  methods: {
    loadMyComments: function () {
      axios({
        url: '/api/user/' + this.uid + '/comments',
        method: 'post',
      }).then(res => {
        if (res.data.stateEnum.state === 7) {
          // console.log(res.data.returnObject)
          for (var com of res.data.returnObject) {
            this.comments.push({
              iid: com.iid,
              item: com.itemName,
              content: com.content,
              createdAtString: com.createdAtString,
              rating: com.rating
            })
          }
        }
      })
    }
  }
}
</script>