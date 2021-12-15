<template>
  <div>
		<van-nav-bar
			title="登录"
			left-arrow
			@click-left="onClickLeft"
		/>
		<van-image width="100" height="100" :src="loginRegisterPic" style="display: inline-block; width: 100%; max-width: 100%; height: auto;"/>
		<van-tabs animated>
			<van-tab title="用户名登录">
				<van-cell-group v-model="loginNameData">
					<van-field
						v-model="loginNameData.username"
						label="用户名"
						placeholder="请输入用户名"
					/>
					<van-field
						v-model="loginNameData.password"
						label="密码"
						type="password"
						placeholder="请输入密码"
					/>
				</van-cell-group>	
				<van-row gutter="20" style="margin: 16px;">		
					<van-col span="12">
						<van-button round type="primary" plain size="large" to="/register">
							注册
						</van-button>	
					</van-col>
					<van-col span="12">
						<van-button round type="primary" size="large" @click="loginName">
							登录
						</van-button>	
					</van-col>	
				</van-row>
			</van-tab>
			<van-tab title="邮箱登录">
				<van-cell-group v-model="loginEmailData">
					<van-field
						v-model="loginEmailData.useremail"
						label="邮箱"
						placeholder="请输入邮箱"
					/>
					<van-field
						v-model="loginEmailData.password"
						label="密码"
						type="password"
						placeholder="请输入密码"
					/>
				</van-cell-group>					
				<van-row gutter="20" style="margin: 16px;">		
					<van-col span="12">
						<van-button round type="primary" plain size="large" to="/register">
							注册
						</van-button>	
					</van-col>
					<van-col span="12">
						<van-button round type="primary" size="large" @click="loginEmail">
							登录
						</van-button>	
					</van-col>	
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
			onClickLeft: () => this.$router.push("/"),
			loginRegisterPic: loginRegisterPic,
			loginNameData: {
				username: '',
				password: ''
			},
			loginEmailData: {
				useremail: '',
				password: ''
			}
		}
	},
	methods: {
		loginName: function() {
			if(this.loginNameData.username === '') {
				Notify({ type: 'primary', message: '请输入用户名' })
			} else if(this.loginNameData.password === '') {
				Notify({ type: 'primary', message: '请输入密码' })
			} else {
				axios({
					url: '/api/login/name',
					method: 'post',
					transformRequest: [function (data) {
							return Qs.stringify(data)
					}],
					data: this.loginNameData
      	}).then(res =>{
					console.log(res.data.stateEnum);
					let resState = res.data.stateEnum.state				
					if(resState === 1) {
						Notify({ type: 'primary', message: '密码错误' })
					} else if(resState === 0) {
						Notify({ type: 'primary', message: '登录成功' })
						this.$router.push("/")
					}
      	})
			}
		},
		loginEmail: function() {
			if(this.loginEmailData.useremail === '') {
				Notify({ type: 'primary', message: '请输入邮箱' })
			} else if(this.loginEmailData.password === '') {
				Notify({ type: 'primary', message: '请输入密码' })
			} else {
				axios({
					url: '/api/login/email',
					method: 'post',
					transformRequest: [function (data) {
							return Qs.stringify(data)
					}],
					data: this.loginEmailData
      	}).then(res =>{
					console.log(res.data.stateEnum);
					let resState = res.data.stateEnum.state				
					if(resState == 1) {
						Notify({ type: 'primary', message: '密码错误' })
					} else if(resState == 0) {
						Notify({ type: 'primary', message: '登录成功' })
						sessionStorage.setItem("isLogin", true)
						sessionStorage.setItem("uid", res.data.returnObject)
						this.$store.state.userState.isLogin = true
						this.$store.state.userState.myUid = res.data.returnObject
						this.$router.push("/")
					}
      	})
			}
		}
	}
}
</script>