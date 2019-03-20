<template>
  <div class="mybox">
    <div class="box" v-for="item in list" @click="distributionOutcomes(item)" :class="item.statusName=='驳回'?'bohui':''">
      <div><div class="header"></div> <span class="name">{{item.respPersonName}}</span>
        <span class="number">分配商品:{{item.goodsCount}}个</span><span class="jiantou"></span></div>
      <p class="shidiao">市调单号：{{item.empBillCode}}</p>
      <p class="shidiao">市调门店：{{item.opponents}}</p>
      <div class="positio">{{item.statusName}}</div>
      <p class="shidiao" v-if="item.remark!=''&&item.remark!=undefined">驳回说明：{{item.remark}}</p>
    </div>
  </div>
</template>
<script>
  import Bus from '../bus.js'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"distributionOutcomesList",
    data() {
      return {
         list:[{
           goodsCount:"",//
           respPersonName:"",//负责人姓名
           respPersoncode:"",//责任人工号
           empBillCode:"",//市调单号
           opponents:"",
           status:0,
         },
         ]
      };
    },
    created(){
        this.getlist();
    },
    components: {Bus},
    methods: {
        getlist(){
          var item=this.$route.params.item;
          if(item!=undefined) {
            localStorage.setItem('item', JSON.stringify(item));
          }
         if(localStorage.getItem("item")!="undefined"){
             item=JSON.parse(localStorage.getItem("item"));
         }


            var url="/empPricersh/getEmpPricershByBillCode/"+item.billCode+"";
          this.$api.get(url, {}, r => {
            if(!r.success){
              Bus.$emit('addMessgeImg',{code:3,info:r.msg});
              this.$router.go(-1);
            }
             this.list=r.data;
          }, f => {
            Bus.$emit('addMessgeImg',{code:3,info:f.msg});
            this.$router.go(-1);
          })
        },
      distributionOutcomes(item){
        if(item.statusName!="草稿"){
          this.$router.push({
            name: "examineAndApprove",
            params: {item: item}
          })
        }
        else {
          this.$router.push({
            name: "distributionOutcomes",
            params: {item: item}
          })
        }
      }
    }
  };
</script>

<style scoped>
  .positio{
    position: absolute;
    right: 0.5rem;
    top: 1.1rem;
    font-size: 0.3rem;
  }
  .bohui{
    background: red;
  }
  .jiantou{
    width: 0.1rem;
    height:0.2rem;
    background: url("../../assets/jpgs/zhankai@2x.png");
    background-size: 100% 100%;
    display: block;
    float: right;
    margin-top: 0.25rem;
  }
  .shidiao{
    font-size: 0.28rem;
    color: #727272;
    margin-top:0.2rem;
  }
  .number{
    font-size: 0.28rem;
    color: #727272;
    margin-left: 0.26rem;
    line-height: 0.7rem;
  }
  .name{
    font-size: 0.32rem;
    color:#303030;
    float:left;
    margin-left:0.2rem;
    line-height: 0.7rem;
    font-weight: bold;
  }
  .box{
    overflow: hidden;
    padding:0.2rem;
    box-sizing: border-box;
    background: white;
    margin-top: 0.18rem;
    position: relative;
  }
  .header{
    width: 0.7rem;
    height:0.7rem;
    background: url("../../assets/jpgs/header.png");
    background-size: 100% 100%;
    float:left;
  }
</style>
