<template>
  <div>
    <h4>注册</h4>
    <el-form 
      v-model="registerData"
    >
      <el-form-item label="邮箱">
        <el-input 
          v-model="registerData.userEmail" 
          placeholder="邮箱" 
        />
      </el-form-item>
      <el-form-item label="密码">
        <el-input 
          v-model="registerData.userPassword" 
          placeholder="密码" 
        />
      </el-form-item>
    </el-form>
    <el-button @click="register">
      注册
    </el-button>
    
	</div>
</template>

<script>
export default {
  data() {
    return {
      registerData: {
        userEmail: '',
        userPassword: ''
      },
      loginData: {

      }
    }
  },
  methods: {
    register() {
      
    },  
    login() {

    }
  }
}
</script>

<style scoped>

</style>