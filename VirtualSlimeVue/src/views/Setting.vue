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
      <van-popup v-model:show="toMerchantShow" style="width: 90%; height: 80%" round teleport="body" closeable>
        <van-cell-group inset style="margin-top: 10%">
          <h3 style="color: #FB7299">Virtual Slime商家服务协议</h3>
          <p>欢迎您与Virtual Slime平台签署《Virtual Slime商家服务协议》，并使用Virtual Slime商家服务！</p>
          <p>【审慎阅读】在申请注册流程中点击同意此协议之前，应当认真阅读本协议。请您务必审慎阅读、充分理解各条款内容，特别是免除、限制责任的条款。</p>
          <p>【签约动作】当您阅读本协议，点击同意并完成全部注册程序后，我们便认为您已经与本平台达成一致，成为本平台的用户。<b>阅读本协议中如果您有任何不同意的意见，您应该立即停止注册程序。</b></p>

          <h3 style="color: #FB7299">一、定义</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;Virtual Slime平台：指Virtual Slime网的网站。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;Virtual Slime：指Virtual Slime平台经营者的合称。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;Virtual Slime服务：指Virtual Slime平台网站向您提供的各项服务。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;Virtual Slime平台规则：包括在Virtual Slime网站内已经发布以及后续发布的全部规则、解读、公告等形式的内容以及平台在论坛、帮助中心等发布的各类规则、实施细则、产品流程的说明、公告等。</p>

          <h3 style="color: #FB7299">二、协议范围</h3>
          <p>【平等主体】本协议由您与Virtual Slime平台经营者共同缔结，本协议对您和Virtual Slime平台经营者都具有合同效力。</p>
          <p>【主体信息】Virtual Slime平台经营者是指经营平台的各法律主体。本协议项下，Virtual Slime平台经营者可能根据平台业务调整而发生变更，变更后平台经营者继续与您旅行本协议并向您提供服务，变更不会影响到您本协议项下的利益。同时Virtual Slime平台经营者还会提供新的平台服务，如果您使用了平台的新增服务，视为您同意服务新增后与平台经营者继续履行协议。<b>发生争执时您可以根据您具体使用的服务及对您权益产生影响的具体行为对象确定与您履约的主体及争议相对方。</b></p>

          <h3 style="color: #FB7299">三、商家账户申请与使用</h3>
          <h4>3.1商家资格</h4>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;您确认，在您开始注册程序使用Virtual Slime平台服务前，您应当具备中华人民共和国法律规定的与您行为相适应的民事行为能力。若您不具备，您及您的监护人应依照法律规定承担因此导致的一切后果。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;此外，您还需确保您不是任何国家、地区或国际组织实施的贸易限制、经济制裁或其他法规限制的对象，也未直接或间接为前述对象提供任何服务，否则您应当停止使用本平台服务，同时您理解违反前述要求可能会造成您无法完成注册及使用本平台服务。</p>
          <h4>3.2账户说明</h4>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;【账户获得】当您阅读完本协议并按照商家申请界面提示完成申请流程后，您可以获得Virtual Slime平台的商家账户并成为Virtual Slime平台的认证商家。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;【账户使用】您用权利使用您设置的账号、密码登录Virtual Slime平台发布商品。</p>

          <h3 style="color: #FB7299">四、Virtual Slime平商家服务及规范</h3>
          <h4>4.1 发布商品/撤销商品</h4>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;Virtual Slime平台为您提供了建立店铺、发布商品、撤销商品、修改商品信息等功能，以更好地匹配您的需求。</p>

          <h4>4.2获取收益</h4>
          <p>当您在本平台发布商品并有人购买了您的商品后，您会按照商品的标价获取同等价值的收益，保留到平台中。为统一规划平台内所有商家的收益，本平台将按月保留您的当月收益，在每个月的最后一天统一发放到您的个人账户。</p>

          <h4>4.3举办促销活动</h4>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;您可以在您的店铺中举办促销活动，对参与活动的商品进行打折出售、批量发售等操作。</p>

          <h4>4.4为商品建立标签</h4>
          <p>您可以使用本平台为您提供的商品标签为商品贴上标签，以便消费者能够通过标签迅速检索到您的商品。</p>

          <h3 style="color: #FB7299">五、商品信息规范及授权</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;【商品的发布】您声明并保证，您对您发布的商品拥有相应、合法的权利。否则，平台可以对您发布的商品依法或依本协议进行删除。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;【禁止性信息】您应当确保您所发布的信息不包含以下的信息：<br>
            1.违反国家法律法规禁止性规定的；<br>
            2.政治宣传、封建迷信、淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的；<br>
            3.欺诈、虚假、不准确或存在误导性的；<br>
            4.侵犯他人知识产权或涉及第三方商业秘密及其他专有权利的；<br>
            5.侮辱、诽谤、恐吓、涉及他人隐私等侵害他人合法权益的；<br>
            6.存在可能破坏、篡改、删除、影响Virtual Slime平台任何系统正常运行或未经授权秘密获取Virtual Slime平台及其他用户的数据、个人资料的病毒、木马、爬虫等恶意软件、程序代码的；<br>
            7.其他违背社会公共利益或公共道德或依据相关Virtual Slime平台协议、规则的规定不适合在Virtual Slime平台上发布的。<br>
          </p>

          <h3 style="color: #FB7299">六、商家违约及处理</h3>
          <h4>6.1违约认定</h4>
          <p>发生如下情形之一的，视为您违约：<br>
            1.使用Virtual Slime平台服务时违反有关法律法规规定的；<br>
            2.使用Virtual Slime平台服务时违反本协议约定的。<br>
          </p>
          <h4>6.2违约处理</h4>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;【商品处理】您在Virtual Slime平台上发布的商品构成违约的，Virtual Slime可以根据相应规则立即对相应商品进行删除处理。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;【行为限制】您在Virtual Slime平台上实施了违规行为，平台可以依据其严重程度，对您的账户进行封号处理。</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;【举报机制】您在Virtual Slime平台上发现其他商家存在违规行为，可以对其进行举报，平台核实后会对存在违规行为的商家进行处理。</p>
          <div style="margin: 10%;">
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