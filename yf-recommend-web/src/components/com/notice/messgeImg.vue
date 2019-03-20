<template>
  <div  class="messge-img" v-show="showImg.length>0"><!--v-show="showList.length>0"-->
    <div v-for="item in showImg">
      <div :class="messgebg[item.code]"></div>
      <div class="messge-info">{{item.info}}</div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'messgeImg',
  data () {
    return {
        messgebg:['messge-success','messge-warning', 'messge-prohibit', 'messge-cancel', 'messge-text']
    }
  },
  props: ['showImg'],
  components: {
  },
  created:function(){
  },
  computed:{

  },
  methods:{
  }
}
</script>
<style scoped>
/*  @import "../../../../static/css/com/notice/messge.css" ;*/
  .messge-img{
    position: fixed;
    z-index: 9999;
    height: 3rem;
    width: 3rem;
    top:40%;
    left:50%;
    margin-top: -1.5rem;
    margin-left: -1.5rem;
    background: rgba(60, 63, 65, 0.63);
    border-radius: 0.2rem;
  }
  .messge-success div{
    margin-top: 2.28rem;
  }
  .messge-success{
    width: 1.5rem;
    height: 1.5rem;
    margin: auto;
    margin-top: 0.47rem;
    margin-bottom: 0.2rem;
    background: url("../../../assets/jpg/success.png") no-repeat;
    background-size: 100% 100%;
    background-position-x: 50%;
    background-position-y: 50%;
  }
  .messge-warning div{
    margin-top: 2.28rem;
  }
  .messge-warning{
    width: 1.5rem;
    height: 1.5rem;
    margin: auto;
    margin-top: 0.47rem;
    margin-bottom: 0.2rem;
    background: url("../../../assets/jpg/fail.png") no-repeat;
    background-size: 100% 100%;
    background-position-x: 50%;
    background-position-y: 50%;
  }
  .messge-prohibit div{
    margin-top: 2.28rem;
  }
  .messge-prohibit{
    width: 1.5rem;
    height: 1.5rem;
    margin: auto;
    margin-top: 0.47rem;
    margin-bottom: 0.2rem;
    background: url("../../../assets/jpg/ban.png") no-repeat;
    background-size: 100% 100%;
    background-position-x: 50%;
    background-position-y: 50%;
  }
  .messge-cancel div{
    margin-top: 2.28rem;
  }
  .messge-cancel{
    width: 1.5rem;
    height: 1.5rem;
    margin: auto;
    margin-top: 0.47rem;
    margin-bottom: 0.2rem;
    background: url("../../../assets/jpg/cancel.png") no-repeat;
    background-size: 100% 100%;
    background-position-x: 50%;
    background-position-y: 50%;
  }
  .messge-text{
    margin-top: 0.5rem;
    background: red;
    color: #FFF;
  }
  .messge-info{
    color: #FFF;
    font-size: 0.3rem;
    line-height: 0.5rem;
    text-align: center;
    height: 0.5rem;
  }
</style>
