<!--  引用hutml
      <popup v-show="popupobj.show" @callback="whether">
        <span slot="title">{{popupobj.title}}</span>
        <span slot="messge">{{popupobj.messge}}</span>
        <span slot="cancel">{{popupobj.cancel}}</span>
        <span slot="confirm">{{popupobj.confirm}}</span>
      </popup>
      数据
      popupobj:{show:false,title:'提示信息',messge:'',cancel:'取消',confirm:'确认'}//弹窗-->
<template>
  <div class="popup" v-show="popupobj.show">
    <div class="popup-bg" :style="{height:popupHeight+'px',width:popupWidth+'px'}" ></div>
    <div class="popup-messge">
      <div class="popup-info">
        <h5><slot name="title">温馨提示</slot></h5>
        <p>
         <slot name="messge">您确定要取消本次预订吗？</slot>
        </p>
      </div>
      <div class="popup-group">
        <button class="popup-button left-button" :class="showdif==0?'default-button':''" @click="choice(0)"><slot name="cancelt">取消</slot></button><button class="popup-button right-button" :class="showdif==1?'default-button':''" @click="choice(1)"><slot name="confirm">确认</slot></button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
/*  title 提示信息
  messge 提示信息
  cancel 取消
  confirm 确认*/
  name: 'popup',
  data () {
    return {
        showdif:1,
      popupHeight:window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight,
      popupWidth:window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
    }
  },
  props: ['popupobj'],
  components: {
  },
  created:function(){
  },
  computed:{
  },
  methods:{
    choice:function(number){
/*        this.showdif=number*/
        this.$emit('callback',number)
    },
  },
}
</script>
<style scoped>
  @import "../../../../static/css/com/notice/popup.css" ;
  .popup-bg{
    background:black;
    opacity: 0.5;
    position: fixed;
    top: 0;
    left: 0;
    z-index:88888;
  }
  .popup-messge{
    font-size: 0.26rem;
    background: white;
    border-radius: 0.12rem;
    width: 5.2rem;
    height: 3.2rem;
    position: fixed;
    top: 50%;
    left: 50%;
    z-index:99999;
    margin-top: -3rem;
    margin-left: -2.6rem;
  }
  .popup-messge h5{
    padding-top: 0.48rem;
    font-size: 0.34rem;
    /*height: 0.68rem;*/
    line-height: 0.68rem;
    text-align: center;
  }
  .popup-messge p{
    font-size: 0.3rem;
    color: #202020;
    text-align: center;
    height: 0.68rem;
    line-height: 0.68rem;
    margin-bottom: 0.48rem;
  }
  .popup-group{
    border-top: 1px solid #F5F5F9;
  }
  .popup-button{
    font-size: 0.38rem;
    height: 0.9rem;
    width: 2.6rem;
    border: none;
    background: #FAFAFA;
    color:#999;
  }
  .right-button{
    border-bottom-right-radius: 0.12rem;
    border-right: 0.01rem solid #EEEEEE;
  }
  .left-button{
    border-bottom-left-radius: 0.12rem;
    border-right: 1px solid #F5F5F9;
  }
  .default-button{
    color: #2479E5;
  }

</style>
