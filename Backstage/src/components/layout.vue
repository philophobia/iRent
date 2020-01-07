<template>
    <el-container>
        <el-header>
            <el-menu mode="horizontal" style="width: auto;margin: 0;padding: 0;" background-color="#3e3281" text-color="#fff" active-text-color="#ffd-4b">
                <img src="../assets/house-white.png" style="height: auto;width: 55px;" @click="goIndexPage">
                <el-submenu style="float: right;" index="s">
                    <template slot="title">{{ admin }}<icon class="el-icon-setting"></icon></template>
                    <el-menu-item index="o" @click="exit"><icon class="el-icon-remove-outline"></icon>退出</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-header>
        <el-container>
            <el-aside width="auto">
                <el-menu style="height: 100%" background-color="#91a0e2" text-color="#000" active-text-color="#ffdead" v-model="isCollapse" :collapse="isCollapse">
                    <el-menu-itme index="0" @click="handleCollapse" style="cursor: pointer;"><icon class="el-icon-menu"></icon></el-menu-itme>
                    <el-menu-item index="1" @click="goUserPage"><icon class="el-icon-user"></icon><span slot="title">用户一览</span></el-menu-item>
                    <el-menu-item index="2" @click="goHousePage"><icon class="el-icon-office-building"></icon><span slot="title">房源一览</span></el-menu-item>
                    <el-menu-item index="3" @click="goDealPage"><icon class="el-icon-goods"></icon><span slot="title">交易一览</span></el-menu-item>
                    <el-menu-item index="4" @click="goReportPage"><icon class="el-icon-edit-outline"></icon><span slot="title">举报一览</span></el-menu-item>
                    <el-menu-item index="5" @click="goLogPage"><icon class="el-icon-document"></icon><span slot="title">操作记录</span></el-menu-item>
                </el-menu>
            </el-aside>
            <el-main width="auto">
                <router-view v-if="isRouterAlive"></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<style>
.el-header{
    font-size: 1.2em;
    z-index: 1000;
    width: 100%;
    background-color: #3e3281;
}
.el-aside{
    text-align: center;
    line-height: 60px;
    padding-top: 60px;
}
.el-container{
    position: absolute;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}
.el-header .el-submenu{
    font-size: 1.2em;
}
.el-aside .el-menu-item{
    font-size: 1.2em;
}
.el-main{
    padding-top: 60px;
}
</style>

<script>
import api from '../apis'
export default {
    provide () {
        return {
            reload: this.reload
        }
    },
    data () {
        return {
            isCollapse: false,
            isRouterAlive: true,
            admin: sessionStorage.getItem("adminID")
        }
    },
    mounted () {
        setTimeout(this.handleTimeout, 900000);
        window.addEventListener("beforeunload", e => this.exit());
    },
    methods: {
        reload: function() {
            this.isRouterAlive = false;
            this.$nextTick(function() {
                this.isRouterAlive = true;
            });
        },
        handleCollapse: function() {
            if (this.isCollapse == false)
                this.isCollapse = true;
            else
                this.isCollapse = false;
        },
        goIndexPage: function(){
            this.$router.push('/index');
        },
        goUserPage: function(){
            this.$router.push('/users');
        },
        goDealPage: function(){
            this.$router.push('/deals');
        },
        goHousePage: function(){
            this.$router.push('/houses');
        },
        goReportPage: function(){
            this.$router.push('/reports');
        },
        goLogPage: function(){
            this.$router.push('/logs');
        },
        exit: function(){
            api.exit().then(response => {
                if (response.data != null) {
                    sessionStorage.clear();
                    this.$router.replace({ path: '/login' });
                }
            }).catch(error => {
                console.log(error);
            });
        },
        handleTimeout: function() {
            this.exit();
            this.$alert('会话超时, 请重新登录。', '提示', {
                confirmButtonText: '确定',
                callback: () => {}
            });
        }
    }
}
</script>