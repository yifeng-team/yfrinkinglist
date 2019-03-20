// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import wcSwiper from 'wc-swiper'


import 'wc-swiper/style.css'
// import 'element-ui/lib/theme-default/index.css'
Vue.use(Element)
Vue.use(wcSwiper);

// 引用API文件
import api from './api/index.js'
Vue.prototype.$api = api
Vue.config.productionTip = false


import VueMatomo from "../static/vue-matomo-0.1.5/src/index.js"



//路由切换
router.beforeEach((to,form,next) =>{
  window.document.title=to.meta.title;
  Vue.use(VueMatomo, {
    // 这里配置你自己的piwik服务器地址和网站ID
    host: 'http://192.168.254.95:8081/matomo',
    siteId:1,
    // 根据router自动注册
    router: router,
    // 是否需要在发送追踪信息之前请求许可
    // 默认false
    requireConsent: false,
    // 是否追踪初始页面
    // 默认true
    trackInitialView: false,
    // 最终的追踪js文件名
    // 默认 'piwik'
    trackerFileName: 'piwik'
  })
window.document.getElementById("isshow").style.display="block";
setTimeout(function(){
  window.document.getElementById("isshow").style.display="none";
},1000)
next();
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
