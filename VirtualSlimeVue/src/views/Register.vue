<template>
  <div>
		<van-nav-bar
			title="注册"
			left-arrow
			@click-left="onClickLeft"
		/>
		<van-image width="100" height="100" :src="loginRegisterPic" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
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
				<van-row gutter="20" style="margin: 16px;">		
						<van-button round type="primary" size="large" @click="register">
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
import loginRegisterPic from '@/assets/images/swipe3.jpeg'
export default {
	data() {
		return {
			onClickLeft: () => history.back(),
			loginRegisterPic: loginRegisterPic,
			useremail: '',
			password1: '',
			password2: ''	
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
					if(resState == 1) {
						Notify({ type: 'primary', message: '密码太简单了，不可以' })
					} else if(resState == 2) {
						Notify({ type: 'primary', message: '这个邮箱已经被用过了' })
					} else if(resState == 0) {
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

<style scoped>

</style>
