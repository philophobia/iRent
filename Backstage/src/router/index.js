import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: resolve =>require(['../components/login'], resolve),
      beforeEnter:(to, from, next) => {
        let auth = sessionStorage.getItem("allowable");
        if (auth) {
          next('/index');
        }
        else {
          next();
        }
      }
    },
    {
      path: '/index',
      component: resolve => require(['../components/layout'], resolve),
      beforeEnter:(to, from, next) => {
        let auth = sessionStorage.getItem("allowable");
        if (!auth) {
          next('/login');
          this.$message({
            type: 'info',
            message: '请先登录'
          });
        }
        else {
          next();
        }
      },
      children: [{
        path: '/',
        component: resolve => require(['../components/index'], resolve)
      },{
        path: '/users',
        component: resolve => require(['../components/users'], resolve)
      },
      {
        path: '/reports',
        component: resolve => require(['../components/reports'], resolve)
      },
      {
        path: '/houses',
        component: resolve => require(['../components/houses'], resolve)
      },
      {
        path: '/logs',
        component: resolve => require(['../components/logs'], resolve)
      }]
    }
  ],
  mode: 'history'
});

export default router