<template>
  <div id="register" v-title data-title="注册">
    <!--<video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
          <source src="../../static/vedio/sea.mp4" type="video/mp4">
      </video>-->

    <div class="me-login-box me-login-box-radius">
      <h1>注册</h1>

      <el-form ref="userForm" :model="userForm" :rules="rules">
   
        <el-form-item prop="account">
          <el-input placeholder="用户名" v-model="userForm.account"></el-input>
        </el-form-item>

         <el-form-item prop="nickname">
          <el-input placeholder="昵称" v-model="userForm.nickname"></el-input>
        </el-form-item>

        <el-form-item prop="mobilephonenumber">
          <el-input type="text" placeholder="手机号" v-model="userForm.mobilephonenumber"></el-input>
        </el-form-item>
 
        <el-form-item prop="code">
          <el-input type="text" placeholder="验证码" v-model="userForm.code"></el-input>
        </el-form-item>

          <el-form-item size="small" class="me-login-button">
          <el-button type="primary" @click="sendsms">发送验证码</el-button>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="密码" v-model="userForm.password" show-password></el-input>
        </el-form-item>


        <el-form-item size="small" class="me-login-button">
          <el-button type="primary" @click="register">注册</el-button>
        </el-form-item>
      </el-form>


    </div>
  </div>
</template>

<script>
  import {register} from '@/api/login'
  import register1 from '@/api/login'

  export default {
    name: 'Register',
    data() {
      return {
        userForm: {
          account: '',
          nickname: '',
          password: '',
        },
        code: '',
        mobilephonenumber: '',
        rules: {
          account: [
            {required: true, message: '请输入昵称', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          nickname: [
            {required: true, message: '请输入昵称', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          mobilephonenumber: [
            {required: true, message: '手机号不能为空', trigger: 'blur'},
            {max: 11, message: '不能大于11个字符', trigger: 'blur'}
          ]
       
        }

      }
    },
    methods: {
          sendsms(){
     register1.sendsms(this.userForm.mobilephonenumber).then(res=> {
        this.$message({message: '验证码发送成功', type: 'success', showClose: true}
        );
      }).catch((error) => {
              if (error !== 'error') {
                this.$message({message: error, type: 'error', showClose: true});
              }
            })
     
          },
      register() {
        register1.register(this.userForm,this.userForm.code).then(res => {
              this.$message({message: '注册成功 快来登录吧', type: 'success', showClose: true});
              this.$router.push({path: '/Login'})
            }).catch((error) => {
              if (error !== 'error') {
                this.$message({message: error, type: 'error', showClose: true});
              }
            })

      }

    }
  }
</script>

<style scoped>
  #login {
    min-width: 100%;
    min-height: 100%;
  }

  .me-video-player {
    background-color: transparent;
    width: 100%;
    height: 100%;
    object-fit: fill;
    display: block;
    position: absolute;
    left: 0;
    z-index: 0;
    top: 0;
  }

  .me-login-box {
    position: absolute;
    width: 300px;
    height: 420px;
    background-color: rgb(255, 255, 255);
    margin-top: 150px;
    margin-left: -180px;
    left: 50%;
    padding: 60px;
  }

  .me-login-box-radius {
    border-radius: 10px;
    box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
  }

  .me-login-box h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    vertical-align: middle;
  }

  .me-login-design {
    text-align: center;
    font-family: 'Open Sans', sans-serif;
    font-size: 18px;
  }

  .me-login-design-color {
    color: #5FB878 !important;
  }

  .me-login-button {
    text-align: center;
  }

  .me-login-button button {
    width: 100%;
  }

</style>
