<template>
  <div class="middle-body">
     <!--<div class="bigbox">-->
       <!--<div class="right"  @click="Marklist()">市调单</div>-->
     <!--</div>-->
    <!--<div class="bigbox">-->
      <!--<div class="left" style="display: none" @click="Pending()">待处理</div>-->
      <!--<div class="right"  @click="Pending()">我的市调单</div>-->
    <!--</div>-->

  </div>
</template>
<script>
  import Bus from './bus.js'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"ChoiceIndex",
    data() {
      return {

      };
    },
    components: {Bus},
    methods: {
      getpage(){
          var i=0;
          var _this=this;
       var time=setInterval(function () {
         if(JSON.parse(localStorage.getItem('infoess')).personCode!=''){
             _this.getvalue();
             clearInterval(time);
         }
         i++;
         if(i>=500){
           Bus.$emit('addMessgeImg',{code:3,info:"网络错误"});
           clearInterval(time);
         }
       },10)
      },
      getvalue(){
        if(JSON.parse(localStorage.getItem('infoess')).departmentCode.length<=5&&JSON.parse(localStorage.getItem('infoess')).personCode!=''){
          if(JSON.parse(localStorage.getItem('infoess')).shopManager=="1"){
            this.$router.push({
              name: "Marklist",
              params: {}
            })
          }
          else{
            this.$router.push({
              name: "Pending",
              params: {}
            })
          }
        }
        else{
          Bus.$emit('addMessgeImg',{code:3,info:"此人无权限"});
        }
      },
    },
    created(){
       this.getpage();
    }
  };
</script>

<style scoped>
.bigbox{
  overflow:hidden;
}
  .left{
    width:2rem;
    height:2rem;
    margin: 0 auto;
  }
.right{
  width:2rem;
  height:2rem;
  margin: 0 auto;
  margin-top: 0.7rem;
}
</style>
