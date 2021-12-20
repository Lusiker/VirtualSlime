<template>
  <div>
    <van-nav-bar
        title="账号资料"
        left-arrow
        @click-left="onClickLeft"
    />
    <van-image width="100" height="100" :src="require('@/assets/images/settingBanner.jpg')" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
    <van-cell-group>
      <van-cell title="头像" is-link @click="updateAvatarShow = true"/>
      <van-popup v-model:show="updateAvatarShow" position="bottom" :style="{ height: '30%' }" round>
        <div style="margin: 5%; text-align: center;">
          <van-uploader
              multiple
              :max-count="1"
              v-model="update.uploadAvatar"
              :after-read="afterLoad"
              :max-size="1024 * 1024"
              @oversize="onOversize"
          />
          <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
            <van-button type="primary" block @click="updateAvatarConfirm">保存</van-button>
          </div>
        </div>
      </van-popup>

      <van-cell title="昵称" :value="info.name" is-link @click="updateNameShow = true"/>
      <van-popup v-model:show="updateNameShow" position="bottom" :style="{ height: '30%' }" round>
        <van-cell-group inset style="margin-top: 10%">
          <van-field v-model="update.name" label="修改昵称" placeholder="请输入新昵称" />
          <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
            <van-button type="primary" block @click="updateNameConfirm">保存</van-button>
          </div>
        </van-cell-group>
      </van-popup>

      <van-cell title="性别" :value="info.sex" is-link @click="updateSexShow = true"/>
      <van-popup v-model:show="updateSexShow" position="bottom" round>
        <van-picker
          :columns="sexColumns"
          @cancel="updateSexShow = false"
          @confirm="updateSexConfirm"
        />
      </van-popup>

      <van-cell title="出生年月" :value="info.birthday" is-link @click="updateBirthdayShow = true"/>
      <van-calendar
        v-model:show="updateBirthdayShow"
        color="#FB7299"
        @confirm="updateBirthdayConfirm"
        first-day-of-week="1"
        :default-date="new Date(2000,0,1)"
        :min-date="new Date(1980,0,1)"
        :max-date="new Date()"
      />

      <van-cell title="个性签名" :value="info.introduction" is-link @click="updateIntroShow = true"/>
      <van-popup v-model:show="updateIntroShow" position="bottom" :style="{ height: '40%' }" round>
        <van-cell-group inset style="margin-top: 10%">
          <van-field
           v-model="update.introduction"
           label="修改个性签名"
           rows="3"
           type="textarea"
           maxlength="50"
           show-word-limit
          />
          <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
            <van-button type="primary" block @click="updateIntroConfirm">保存</van-button>
          </div>
        </van-cell-group>
      </van-popup>
    </van-cell-group>
    <van-cell-group style="margin-top: 10%">
      <van-cell title="UID" :value="info.uid" />
      <van-cell title="商家认证" :value="info.isMerchant" />
      <van-cell title="账号状态" :value="info.state" />
    </van-cell-group>
    <van-cell-group style="margin-top: 10%">
      <van-cell
          icon="question-o"
          title="想要卖你的商品吗？点这里就对了"
          is-link @click="toMerchantShow = true"
          v-if="info.isMerchant === '未认证' && info.state === '正常'" />
      <van-popup v-model:show="toMerchantShow" position="bottom" :style="{ height: '80%' }" round>
        <van-cell-group inset style="margin-top: 10%">
          Lorem
          <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
            <van-button type="primary" block @click="toMerchant">开始你的Virtual Slime商家之路</van-button>
          </div>
        </van-cell-group>
      </van-popup>
      <van-cell
          icon="question-o"
          title="你还没有验证邮箱，目前账号功能受限中"
          is-link @click="toActivateShow = true"
          v-if="info.state === '受限制'" />
      <van-popup v-model:show="toActivateShow" position="bottom" :style="{ height: '30%' }" round>
        <div style="margin-top: 10%">
          <van-cell-group inset :title="'当前邮箱：' + info.email">
            <van-field v-model="info.checkCode" label="验证码" placeholder="请填入验证码" >
              <template #button>
                <van-button size="small" plain type="primary" @click="toActivateSend">发送验证码</van-button>
              </template>
            </van-field>
            <div style="margin-top: 10%; margin-left: 18px; margin-right: 18px">
              <van-button type="primary" block @click="toActivateCode">激活</van-button>
            </div>
          </van-cell-group>
        </div>
      </van-popup>
    </van-cell-group>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios'
import { Notify } from 'vant';
import Qs from "qs";
export default {
  data() {
    return {
      onClickLeft: () => history.back(),
      updateAvatarShow: false,
      updateNameShow: false,
      updateBirthdayShow: false,
      updateIntroShow: false,
      updateSexShow: false,
      toMerchantShow: false,
      toActivateShow: false,
      sexColumns: ['未选择', '男', '女', '保密'],
      info: {
        uid: sessionStorage.getItem("uid"),
        name: sessionStorage.getItem("username"),
        sex: this.returnSex(),
        birthday: sessionStorage.getItem("birthday") !== "null" ? sessionStorage.getItem("birthday") : '未选择',
        introduction: sessionStorage.getItem("introduction"),
        isMerchant: sessionStorage.getItem("isMerchant") === "true" ? '已认证' : '未认证',
        state: this.returnState(),
        email: sessionStorage.getItem("useremail"),
        checkCode: ''
      },
      update: {
        uploadAvatar: ref([ ]),
        avatarBase64: '',
        name: sessionStorage.getItem("username"),
        sex: '',
        birthday: '',
        introduction: sessionStorage.getItem("introduction"),
      }
    }
  },
  methods: {
    returnState: function () {
      let state = sessionStorage.getItem("state")
      const statesMap = {
        'RESTRICTED': '受限制',
        'NORMAL': '正常',
        'BANNED': '封禁中'
      }
      return statesMap[state]
    },
    returnSex: function () {
      let sex = sessionStorage.getItem("sex")
      const sexMap = {
        'UNDEFINED': '未选择',
        'MALE': '男',
        'FEMALE': '女',
        'SECRET': '保密'
      }
      return sexMap[sex]
    },
    afterLoad: function (file) {
      // console.log(file.content)
      this.update.avatarBase64 = file.content
    },
    onOversize: function () {
      Notify({type: 'primary', message: '图片大小不能超过1MB'})
    },
    updateAvatarConfirm: function () {
      this.update.uploadAvatar[0].status = 'uploading'
      axios({
        url: '/api/user/' + this.info.uid + '/saveAvatar',
        method: 'post',
        transformRequest: [function (data) {
          return Qs.stringify(data)
        }],
        data: {
          data: this.update.avatarBase64
        }
      }).then(() =>{
        sessionStorage.setItem("isAvatarChanged", 'true')
        this.updateAvatarShow = false
        Notify({type: 'primary', message: '修改成功'})
      })
    },
    updateNameConfirm: function () {
      axios({
        url: '/api/user/' + this.info.uid + '/update/name=' + this.update.name,
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 2) {
          sessionStorage.setItem("username", res.data.returnObject)
          this.info.name = sessionStorage.getItem("username")
          Notify({type: 'primary', message: '修改成功'})
          this.updateNameShow = false
        } else if(res.data.stateEnum.state === -4) {
          Notify({type: 'primary', message: '昵称重复'})
        }
      })
    },
    updateSexConfirm: function (value) {
      this.updateSexShow = false
      axios({
        url: '/api/user/' + this.info.uid + '/update/sex=' + this.sexColumns.indexOf(value),
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 2) {
          sessionStorage.setItem("sex", res.data.returnObject)
          this.info.sex = this.returnSex()
          Notify({type: 'primary', message: '修改成功'})
        }
      })
    },
    updateBirthdayConfirm: function (value) {
      this.updateBirthdayShow = false
      let bir = `${value.getFullYear()}-${value.getMonth() + 1}-${value.getDate()}`;
      axios({
        url: '/api/user/' + this.info.uid + '/update/birthday=' + bir,
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 2) {
          sessionStorage.setItem("birthday", res.data.returnObject)
          this.info.birthday = sessionStorage.getItem("birthday")
          Notify({type: 'primary', message: '修改成功'})
        }
      })
    },
    updateIntroConfirm: function () {
      this.updateIntroShow = false
      axios({
        url: '/api/user/' + this.info.uid + '/update/intro=' + this.update.introduction,
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 2) {
          sessionStorage.setItem("introduction", res.data.returnObject)
          this.info.introduction = sessionStorage.getItem("introduction")
          Notify({type: 'primary', message: '修改成功'})
        }
      })
    },
    toMerchant: function () {
      this.toMerchantShow = false
      axios({
        url: '/api/user/' + this.info.uid + '/toMerchant',
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 2) {
          sessionStorage.setItem("isMerchant", "true")
          this.info.isMerchant = sessionStorage.getItem("isMerchant")
          Notify({type: 'primary', message: '开启征途吧！'})
        }
      })
    },
    toActivateSend: function () {
      axios({
        url: '/api/user/' + this.info.uid + '/activate',
        method: 'post',
      }).then(res =>{
        //time
      })
    },
    toActivateCode: function () {
      this.toActivateShow = false
      axios({
        url: '/api/user/' + this.info.uid + '/activate/checkCode=' + this.info.checkCode,
        method: 'post',
      }).then(res =>{
        if(res.data.stateEnum.state === 0) {
          sessionStorage.setItem("state", "NORMAL")
          this.info.state = this.returnState()
          Notify({type: 'primary', message: '激活成功'})
        }
      })
    }
  }
}
</script>

<style scoped>

</style>