<template>
  <div>
		<van-nav-bar
			title="注册"
			left-arrow
			@click-left="onClickLeft"
		/>
		<van-image width="100" height="100" :src="require('@/assets/images/registerBanner.jpg')" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
		<van-tabs animated>
			<van-tab title="注册账号">
				<van-cell-group>
					<van-field
						v-model="useremail"
						label="邮箱"
						placeholder="请输入邮箱"
					/>
					<van-field
						v-model="password1"
						label="密码"
						type="password"
						placeholder="请输入密码"
					/>
					<van-field
						v-model="password2"
						label="重复密码"
						type="password"
						placeholder="请输入密码"
					/>
				</van-cell-group>
        <van-checkbox v-model="agree" style="margin: 18px" icon-size="20px" label-disabled>同意<a style="color: #FB7299" @click="show = true">用户条款</a></van-checkbox>
        <van-popup v-model:show="show" style="width: 90%; height: 80%" round teleport="body" closeable>
          <div style="margin: 10%;">
            <h3 style="color: #FB7299">Virtual Slime平台服务协议</h3>
            <p>欢迎您与Virtual Slime平台签署《Virtual Slime平台服务协议》，并使用Virtual Slime平台服务！</p>
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

            <h3 style="color: #FB7299">三、账户注册与使用</h3>
            <h4 style="color: #FB7299">3.1用户资格</h4>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;您确认，在您开始注册程序使用Virtual Slime平台服务前，您应当具备中华人民共和国法律规定的与您行为相适应的民事行为能力。若您不具备，您及您的监护人应依照法律规定承担因此导致的一切后果。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;此外，您还需确保您不是任何国家、地区或国际组织实施的贸易限制、经济制裁或其他法规限制的对象，也未直接或间接为前述对象提供任何服务，否则您应当停止使用本平台服务，同时您理解违反前述要求可能会造成您无法完成注册及使用本平台服务。</p>
            <h4 style="color: #FB7299">3.2账户说明</h4>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;【账户获得】当您阅读完本协议并按照注册界面提示完成注册流程后，您可以获得Virtual Slime平台的账户并成为Virtual Slime平台的用户。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;【账户使用】您用权利使用您设置的账号、密码登录Virtual Slime平台。</p>

            <h3 style="color: #FB7299">四、Virtual Slime平台服务及规范</h3>
            <h4 style="color: #FB7299">4.1 商品浏览和点赞</h4>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Virtual Slime平台为您提供了信息分类、搜索栏、标签分类、点赞的功能，以更好地匹配您的需求。</p>

            <h4 style="color: #FB7299">4.2商品购买与评价</h4>
            <p>当您在本平台完成购买时，请您仔细确认所购商品的品名、价格、限制性要求等重要事项，并核实您下单时的个人信息。如您填写的个人信息不属实，后续造成的全部法律后果由您自己承担。在购买商品后，您有权在Virtual Slime平台的评价系统中，对于您达成交易的商品进行评价。为确保评价内容为消费者提供可靠的购买依据，您的所有评价行为应遵守Virtual Slime平台的相关规定，评论应与商品具有相关性，不包含污言秽语、侵犯法律信息、政治敏感信息等禁止性信息。当您在本平台完成购买时，请您仔细确认所购商品的品名、价格、限制性要求等重要事项，并核实您下单时的个人信息。如您填写的个人信息不属实，后续造成的全部法律后果由您自己承担。在购买商品后，您有权在Virtual Slime平台的评价系统中，对于您达成交易的商品进行评价。为确保评价内容为消费者提供可靠的购买依据，<b>您的所有评价行为应遵守Virtual Slime平台的相关规定，评论应与商品具有相关性，不包含污言秽语、侵犯法律信息、政治敏感信息等禁止性信息。</b></p>

            <h4 style="color: #FB7299">4.3商品加入购物车</h4>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;Virtual Slime平台为您提供购物车服务，在您选择到合适的商品，并完成购买前，可以将商品添加到购物车，等您想要购买时，可以在购物车内选择商品并下单。</p>

            <h3 style="color: #FB7299">五、用户信息规范及授权</h3>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;【信息的发布】您声明并保证，您对您发布的信息拥有相应、合法的权利。否则，平台可以对您发布的信息依法或依本协议进行删除。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;【禁止性信息】您应当确保您所发布的信息不包含以下的信息：<br>
              1.违反国家法律法规禁止性规定的；<br>
              2.政治宣传、封建迷信、淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的；<br>
              3.欺诈、虚假、不准确或存在误导性的；<br>
              4.侵犯他人知识产权或涉及第三方商业秘密及其他专有权利的；<br>
              5.侮辱、诽谤、恐吓、涉及他人隐私等侵害他人合法权益的；<br>
              6.存在可能破坏、篡改、删除、影响Virtual Slime平台任何系统正常运行或未经授权秘密获取Virtual Slime平台及其他用户的数据、个人资料的病毒、木马、爬虫等恶意软件、程序代码的；<br>
              7.其他违背社会公共利益或公共道德或依据相关Virtual Slime平台协议、规则的规定不适合在Virtual Slime平台上发布的。<br>
            </p>

            <h3 style="color: #FB7299">六、用户违约及处理</h3>
            <h4 style="color: #FB7299">6.1违约认定</h4>
            <p>发生如下情形之一的，视为您违约：<br>
              1.使用Virtual Slime平台服务时违反有关法律法规规定的；<br>
              2.使用Virtual Slime平台服务时违反本协议约定的。<br>
            </p>
            <h4 style="color: #FB7299">6.2违约处理</h4>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;【信息处理】您在Virtual Slime平台上发布的信息构成违约的，Virtual Slime可以根据相应规则立即对相应信息进行删除、屏蔽处理。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;【行为限制】您在Virtual Slime平台上实施了违规行为，平台可以依据其严重程度，对您的账户进行禁言或封号处理。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;【举报机制】您在Virtual Slime平台上发现其他用户存在违规行为，可以对其进行举报，平台核实后会对存在违规行为的用户进行处理。</p>
          </div>
        </van-popup>
        <van-row gutter="20" style="margin: 16px;">
          <van-button round type="primary" size="large" @click="register" :disabled="!agree">
            注册
          </van-button>
				</van-row>
			</van-tab>
		</van-tabs>
  </div>
</template>

<script>
import axios from 'axios'
import Qs from 'qs'
import { Notify } from 'vant';
export default {
	data() {
		return {
			onClickLeft: () => history.back(),
			useremail: '',
			password1: '',
			password2: '',
      agree: false,
      show: false
		}
	},
	methods: {
		register: function() {
			if(this.useremail === '') {
				Notify({ type: 'primary', message: '请输入邮箱' })
			} else if(this.password1 === '') {
				Notify({ type: 'primary', message: '请输入密码' })
			} else if(this.password2 === '') {
				Notify({ type: 'primary', message: '请重复密码' })
			} else if(this.password1 !== this.password2){
				Notify({ type: 'primary', message: '密码不一致' })
			} else {
				axios({
					url: '/api/register',
					method: 'post',
					transformRequest: [function (data) {
							return Qs.stringify(data)
					}],
					data: {
						useremail: this.useremail,
						password: this.password1
					}
      	}).then(res =>{
					let resState = res.data.stateEnum.state				
					if(resState === 1) {
						Notify({ type: 'primary', message: '密码太简单了，不可以' })
					} else if(resState === 2) {
						Notify({ type: 'primary', message: '这个邮箱已经被用过了' })
					} else if(resState === 0) {
						Notify({ type: 'primary', message: '注册成功' })
						axios({
							url: '/api/login/email',
							method: 'post',
							transformRequest: [function (data) {
									return Qs.stringify(data)
							}],
							data: {
								useremail: this.useremail,
								password: this.password1
							}
						}).then(res =>{
							sessionStorage.setItem("isLogin", true)
							sessionStorage.setItem("uid", res.data.returnObject)
							this.$store.state.userState.isLogin = true
							this.$store.state.userState.myUid = res.data.returnObject
						})
						this.$router.push("/")
					}
      	})
			}
		}
	}
}
</script>
