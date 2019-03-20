<template>
  <div id="app">
    <!--<div class="tubiaos" :class="{iserror:yes}" @click="starttouch"></div>-->
    <messge :showList="messge"></messge>
    <router-view :info="sitingInfo"/>
    <notice :show="noTice.noTiceShow" @callback="closeNottice">
      {{noTice.noTiceShowMessge}}
    </notice>
    <messgeImg :showImg="messgeInfo"></messgeImg>
    <gobacks v-show="showicon"></gobacks>
  </div>
</template>

<script>
  import Bus from './components/bus.js'
  import wx from 'weixin-js-sdk'
  import wechatlib from './api/wechatlib'
  import messgeImg from './components/com/notice/messgeImg'
  import messge from './components/com/notice/messge'
  import notice from './components/com/notice/notice'
  import Vue from 'vue';
  import gobacks from './components/gobacks.vue';
  import {CitySelect} from 'vue-ydui/dist/lib.rem/cityselect';
  import Goback from "./components/public/goback";
  //  import YDUI from 'vue-ydui/ydui.rem.js'
  export default {
    name: 'App',
    data () {
      return {
        senderCode:"",
        showicon:false,
          yes:false,//控制首页图标
        sitingInfo:{
          company: "",
          companyAdmin: "",
          departmentCode: "",
          departmentName: "",
          employeeName: "",
          headCompanyAdmin: "",
          mobile: "",
          job:3,// 职位编码   1 代表门店员工  2代表店员
          personName:"",
          personCode: "",
          shopManager:0,
        },//应用基本信息
        noTice:{
          noTiceShow:false,
          noTiceShowMessge:'提示',},//确认提示信息
        messge:[],//头部提示信息
        messgeInfo:[],//图片提示信息
        baseContextPath: "https://mdwx.yfdyf.com/siting"
      }
    },
    components: {
      Bus,
      Goback,
      notice,messge,messgeImg,gobacks
    },
    watch: {
      $route(to, from) {
       if(this.$route.path.substring(1,this.$route.path.length)=="Marklist"||this.$route.path.substring(1,this.$route.path.length)=="Pending"){
          this.showicon=false;
       }
       else{
         this.showicon=true;
       }
      }
    },
    computed:{
    },
    methods:{
      starttouch:function () {
        if (this.sitingInfo.shopManager=="1") {
          this.$router.push('Marklist')
          return;
        }
        if (this.sitingInfo.shopManager=="0") {
          this.$router.push('Pending')
        }

      },
      //模拟alert
      closeNottice:function(){
        this.noTice.noTiceShow=false;
          this.noTice.noTiceShowMessge=''
      },
      //模拟消息组件
      removeMessge:function(){
        var _this=this
        setTimeout(function(){
          if(_this.messge.length>0){
            _this.messge.splice(0,1)
          }
          _this.removeMessge()
        },3000)
      },
      getQueryStrings:function() {
        var qs = location.search.substr(1), // 获取url中"?"符后的字串
          args = {}, // 保存参数数据的对象
          items = qs.length ? qs.split("&") : [], // 取得每一个参数项,
          item = null,
          len = items.length;

        for(var i = 0; i < len; i++) {
          item = items[i].split("=");
          var name = decodeURIComponent(item[0]),
            value = decodeURIComponent(item[1]);
          if(name) {
            args[name] = value;
          }
        }
        return args;
      },
      getCode:function(){
        var url="";
        if(wechatlib.getQueryString('personCode')==""||wechatlib.getQueryString('personCode')==undefined){
          url="/empPricersh/getUserInfo?personCode="+JSON.parse(localStorage.getItem('info')).senderCode;
        }
        else{
          url="/empPricersh/getUserInfo?personCode="+wechatlib.getQueryString('personCode');
        }
          this.$api.get(url, {}, r => {
            this.sitingInfo=r.data;
            this.sitingInfo.personName=r.data.employeeName;
            localStorage.setItem('infoess', JSON.stringify(this.sitingInfo));

            if(this.sitingInfo.departmentCode.length>5){
              Bus.$emit('addMessgeImg',{code:3,info:"该人员不属于门店"});
              return;
            }

            var i=0;
            var _this=this;
            var time=setInterval(function () {
              if(JSON.parse(localStorage.getItem('infoess')).personCode!=''){
                _this.starttouch();
                clearInterval(time);
              }
              i++;
              if(i>=500){
                Bus.$emit('addMessgeImg',{code:3,info:"网络错误"});
                clearInterval(time);
              }
            },10)
          }, f => {
            Bus.$emit('addMessgeImg',{code:3,info:f.msg});
          })
      },
    },
    created:function(){
      localStorage.setItem('infoess', JSON.stringify(this.sitingInfo));
      this.getCode();
    },
    mounted:function(){
      var _this=this;
      _this.removeMessge();
      //确认提示信息
      Bus.$on('noTice',(noTice)=>{
        _this.noTice.noTiceShow=true;
          _this.noTice.noTiceShowMessge=noTice
      });
      //头部提示信息
      Bus.$on('addMessge',(addMessge)=>{
        _this.messge.push(addMessge)
      });
      //图片提示信息
      Bus.$on('addMessgeImg',(addMessgeImg)=>{
        _this.messgeInfo=[];
        _this.messgeInfo.push(addMessgeImg);
        setTimeout(function(){
          _this.messgeInfo=[]
        },1000)
      })
    },
  }
</script>

<style>
  /*@import "./assets/css/public";*/
  #app {
  font-family: Helvetica, Droid Sans, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 7.5rem;
  margin: auto;
  font-size: 0.32rem;
  /*background: #F8F8F9;*/
  }
  .middle-body{
    width: 7.5rem;
    margin: 0 auto;
  }
</style>

