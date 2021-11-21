<template>
	<el-menu
		:default-active="$route.path"
		class="el-menu-demo"
		mode="horizontal"
		router
	>
		<el-image
        style="height: 60px"
        :src="logo"
        :fit="fit"
      ></el-image>
		<el-menu-item index="/">主站</el-menu-item>
		<el-menu-item index="/about">漫画</el-menu-item>
		<el-menu-item index="2">游戏</el-menu-item>
		<el-menu-item index="3">音乐</el-menu-item>
		<el-menu-item index="4">排名</el-menu-item>		
		<el-input
			v-model="search"
			placeholder="搜索本子"
			class="input-search"
			style="width: 30%;"
		>
			<template #append>
				<el-button :icon="Search"></el-button>
			</template>	
		</el-input>
		<el-popover 
			placement="bottom" 
			:width="150" 
			trigger="hover"
		>
			<template #reference>
				<el-button type="text" style="height: 60px;padding-top: 9px;">
					<el-avatar :src="avatar" fit="cover"></el-avatar>
				</el-button>
			</template>
    	<el-menu
        class="menu-right"
				id="right-menu"
      >
				<el-menu-item index="1" id="message">
          <span>我的消息</span>
        </el-menu-item>
        <el-menu-item index="2" id="message">
          <span>回复我的</span>
        </el-menu-item>
        <el-menu-item index="3" id="message">
          <span>收到的赞</span>
        </el-menu-item>
        <el-menu-item index="4" id="message">
          <span>系统通知</span>
        </el-menu-item>
      </el-menu>
  	</el-popover>
		<el-popover 
			placement="bottom" 
			:width="150" 
			trigger="hover"
		>
			<template #reference>
				<el-button type="text" style="color: black;" id="no-space-button">消息</el-button>
			</template>
    	<el-menu
        class="menu-right"
				id="right-menu"
      >
				<el-menu-item index="1" id="message">
          <span>我的消息</span>
        </el-menu-item>
        <el-menu-item index="2" id="message">
          <span>回复我的</span>
        </el-menu-item>
        <el-menu-item index="3" id="message">
          <span>收到的赞</span>
        </el-menu-item>
        <el-menu-item index="4" id="message">
          <span>系统通知</span>
        </el-menu-item>
      </el-menu>
  	</el-popover>
		<el-popover 
			placement="bottom" 
			:width="150" 
			trigger="hover"
		>
			<template #reference>
				<el-button type="text" style="color: black;" id="no-space-button">收藏</el-button>
			</template>
    	<el-menu
        class="menu-right"
				id="right-menu"
      >
				<el-menu-item index="1" id="message">
          <span>我的消息</span>
        </el-menu-item>
        <el-menu-item index="2" id="message">
          <span>回复我的</span>
        </el-menu-item>
        <el-menu-item index="3" id="message">
          <span>收到的赞</span>
        </el-menu-item>
        <el-menu-item index="4" id="message">
          <span>系统通知</span>
        </el-menu-item>
      </el-menu>
  	</el-popover>
		<el-popover 
			placement="bottom" 
			:width="150" 
			trigger="hover"
		>
			<template #reference>
				<el-button type="text" style="color: black;" id="no-space-button">购物车</el-button>
			</template>
    	<el-menu
        class="menu-right"
				id="right-menu"
      >
				<el-menu-item index="1" id="message">
          <span>我的消息</span>
        </el-menu-item>
        <el-menu-item index="2" id="message">
          <span>回复我的</span>
        </el-menu-item>
        <el-menu-item index="3" id="message">
          <span>收到的赞</span>
        </el-menu-item>
        <el-menu-item index="4" id="message">
          <span>系统通知</span>
        </el-menu-item>
      </el-menu>
  	</el-popover>
		<el-button type="danger" id="push-item">发布商品</el-button>
	</el-menu>
</template>

<script>
import { Search } from '@element-plus/icons'
import logo from '@/assets/pic/logo.jpg'
import avatar from '@/assets/avatar/1.jpg'
export default {
	data() {
		return {
			logo: logo,
			avatar: avatar,
			Search: Search
		}
	},
	methods: {
		
	}
}
</script>

<style>
.el-menu {
	box-shadow: var(--el-box-shadow-light);
}
.input-search {
	position: absolute;
	left: 0;
	right: 0;
	margin: auto;
}
#push-item {
	width: 120px;
	background-color: #FB7299;
	margin-top: 7px;
	margin-bottom: 7px;
	margin-right: 1%;
}
#right-menu {
	box-shadow: none; 
	border-right: none;
	margin-left: -8%;
	margin-right: -8%;
}
#message {
	height: 40px;
}
.el-button__text--expand {
	letter-spacing: 0px !important;
}
.el-button+.el-button {
	margin-left: 30px !important;
}
.el-button.is-circle {
	width: 40px;
  height: 40px;
	margin: 9px;
	padding: 0px !important;
}
</style>