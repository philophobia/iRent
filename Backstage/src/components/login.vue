<template>
    <div class="login_page">
        <div class="form_container">
            <el-form :model="formLogin">
                <el-form-item>
                    <h2 class="title">爱租房-后台</h2>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="formLogin.account" placeholder="账号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="formLogin.password" placeholder="密码" type="password" @keyup.enter.native="verify"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="warning" @click="verify">登录</el-button>
                </el-form-item>
            </el-form>        
        </div>
    </div>
</template>

<style scoped>
.login_page {
    position: relative;
    width: 100%;
    height: 100%;
    text-align: center;
}
.form_container {
    position: absolute;
    border: 2px solid brown;
    box-shadow: 0 0 25px #cac6c6;
    width: 350px;
    height: 300px;
    border-radius: 8px;
    top: 50%;
    left: 50%;
    margin-left: -175px;
    margin-top: -150px
}
.form_container .title {
    text-align: center;
    color: darkslateblue;
}
.form_container .el-form {
    margin: 30px 80px auto 80px;
}
</style>

<script>
import api from '../apis'
import md5 from 'js-md5'
export default {
    data() {
        return {
            formLogin: {
                account: '',
                password: ''
            }
        }
    },
    methods: {
        verify() {
            if (this.formLogin.account == '' || this.formLogin.password == '') {
                this.$message({
                    type: 'error',
                    message: '账号或密码不完整'
                })
                return;
            }
            let c_pwd = md5(this.formLogin.password);
            this.formLogin.password = c_pwd;
            api.enter(this.formLogin)
                .then(response => {
                    if (response && response.data) {
                        sessionStorage.setItem("adminID", this.formLogin.account);
                        sessionStorage.setItem("allowable", true);
                        this.$router.replace({ path: '/index' });
                    }
                    else {
                        this.$message.error('登录失败，请重试。');
                    }
                }).catch(error => {
                    console.log(error);
                });
        }
    }
}
</script>