<template>
  <div class="notice" v-show="show" id="notice" style="z-index: 20">
    <div class="notice-messge">
      <span>
        <slot>
           不要重复提交不要重复提交不
        </slot>
      </span>
      <div class="notice-but">
        <button @click="closeInfo">确认</button>
      </div>
    </div>
    <div :style="{height:'100vh',width:7.50+'rem'}" class="popup-bg"></div>
  </div>
</template>
<script>
export default {
  name: 'notice',
  data () {
    return {
      popupHeight:window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight,
      popupWidth:window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
    }
  },
  props: ['show'],
  components: {
  },
  created:function(){
  },
  computed:{
  },
  methods:{
    closeInfo:function(){
        this.$emit('callback','')
    }
  }
}
</script>
<style scoped>
  @import "../../../../static/css/com/notice/notice.css" ;

  .notice{
    height: 4rem;
    width: 6rem;
    background: white;
    border-radius: 0.2rem;
    position: fixed;
    top:50%;
    left:50%;
    margin-top: -2.7rem;
    margin-left: -2.8rem;
  }
  .notice-messge{
    position: absolute;
    border-radius: 0.12rem;
    z-index: 99999;
    background: white;
    font-size: 0.32rem;
    text-align: center;
    height: 3.4rem;
    width: 5.6rem;
    padding: 0.8rem 0.25rem 0 0.25rem;
    box-sizing: border-box;
  }
  .notice-messge span{
    padding-top: 1rem;
    text-align: center;
  }
  .popup-bg{
    background:black;
    opacity: 0.5;
    position: fixed;
    top: 0;
    left: 0;
    z-index:99998;
    width:100%;
    height:100vh;
    margin-left: 50%;
    transform: translateX(-50%);
  }
  .notice-but button{
    overflow: hidden;
    font-size: 0.36rem;
    line-height: 1rem;
    width: 100%;
    height: 1rem;
    border: none;
    border-bottom-right-radius: 0.2rem;
    border-bottom-left-radius: 0.2rem;
    color: #0289ff;
    background: #fafafa;
    border-top: 1px solid #EEE;
    position: absolute;
    bottom: 0;
    left: 0;
  }
  .notice-but button:active{
    background: #f4f5f6;
  }
  .notice-but{
    line-height: 1rem;
    height: 1rem;
  }

</style>
