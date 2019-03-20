<template>
  <div>
    <div class="stores flexbox" v-if="showdiv">
      <div class="flexs" @click="changestore(item.ENTERPRISENAME,i)" :class="item.chack?'firstcolors':'nocolor'" v-for="item,i in list.competitionStore">
        {{item.ENTERPRISENAME}}
        <p class="heng" v-show="item.chack"></p>
      </div>
    </div>
    <div class="goods" v-for="item,i in list.allGooodNumber" v-show="i==indexnow">
      共{{item.COUNT}}个商品 已完成：<span style="color: #2479E5;">{{nowcompany}}</span>个
    </div>
    <div class="count-box">
      <div class="zi-count" v-for="item,i in list.rows" v-show="newstoreName==item.opponents">
        <div style="overflow: hidden"><p class="titles">  <span class="bohui" v-show="item.status=='4'">驳回</span>{{item.goodName}}</p></div>
        <p class="mendian">商品条码：<span style="color:#303030">{{item.barCode}}</span></p>
        <p class="mendian" style="width: 50%;float: left" v-show="item.selling!=''">售价：<span style="color:#303030">{{item.selling}}</span></p>
        <p class="mendian" style="width: 50%;float: left" v-show="item.memberPrice!=''">会员价：<span style="color:#303030">{{item.memberPrice}}</span></p>
      </div>
    </div>
  </div>
</template>
<script>
  import calculation from "../com/notice/calculation"
  import Bus from '../bus.js'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"productList",
    data() {
      return {
        changecount:1,
        selling:0,
        memberPrice:0,
        list:{},
        nowitem:{},
        showdiv:false,
        indexnow:0,
        nowcompany:0,
      };
    },
    components: {
      calculation,
      Bus,
    },
    methods: {
      findvalue(index){//寻找匹配的对象
        for(var i=0;i<this.list.completeGoodNumber.length;i++) {
          if(this.list.completeGoodNumber[i].ENTERPRISE_CODE==this.list.allGooodNumber[index].ENTERPRISE_CODE) {
            this.nowcompany=this.list.completeGoodNumber[i].COUNT;
            break;
          }
          else{
            this.nowcompany=0;
          }
        }
      },
//      选项卡切换
      changestore(name,index){
          this.findvalue(index);
        this.newstoreName=name;
        var obj={};
        for(var i=0;i<this.list.competitionStore.length;i++){
          obj=this.list.competitionStore[i];
          obj.chack=false;
          this.$set(this.list.competitionStore,i,obj);
        }


        let objs={};
        objs=this.list.competitionStore[index];
        objs.chack=true;
        this.$set(this.list.competitionStore,index,objs);
      },
//    维护单价完成之后完成按钮
      success(){
        this.$router.go(-1);
      },
      yes(){
        let data={};
        data.uuid=this.nowitem.uuid;
        data.retailPrice=this.selling;
        data.memberPrice=this.memberPrice;
        data.personCode=JSON.parse(localStorage.getItem('infoess')).personCode;
        data.personName=JSON.parse(localStorage.getItem('infoess')).personName;
        var url="/empPricersh/updateAreaGoodsPrice";
        this.$api.get(url, data, r => {
          if(!r.success){
            Bus.$emit('addMessgeImg',{code:3,info:r.msg});
            this.$router.go(-1);
          }

          var objt={};
          objt=this.nowitem;
          objt.selling=this.selling;
          objt.memberPrice=this.memberPrice;
          this.$set(this.list.rows,this.nowitem.index,objt);

          this.selling=0;
          this.memberPrice=0;
        }, f => {
          Bus.$emit('addMessgeImg',{code:3,info:f.msg});
        })
      },
      onece(value){
        this.changecount=value;
        if(this.changecount==1){
          this.selling=0;
        }
        else{
          this.memberPrice=0;
        }
      },
      _confirmEvent(res){
        if(this.changecount==1){
          this.selling=res;
          this.changecount=2;
        }
        else{
          this.memberPrice=res;
        }
      },
      getlists(){
        var item=this.$route.params.item;
        var url="/empPricersh/findDeListByEmpBillCode/"+item.empBillCode+"";
        this.$api.get(url, {}, r => {
          if(!r.success){
            Bus.$emit('addMessgeImg',{code:3,info:r.msg});
            this.$router.go(-1);
          }
          var obj={};
          for(var i=0;i<r.data.competitionStore.length;i++){
            obj=r.data.competitionStore[i];
            obj.chack=false;
            r.data.competitionStore[i]=obj;
          }
          this.list=r.data;

          let objs={};
          objs=this.list.competitionStore[0];
          objs.chack=true;
          this.$set(this.list.competitionStore,0,objs);
          this.newstoreName=r.data.competitionStore[0].ENTERPRISENAME;

          if(this.list.competitionStore.length>1){
              this.showdiv=true;
          }
          this.findvalue(0);
        }, f => {
          Bus.$emit('addMessgeImg',{code:3,info:f.msg});
        })
      }
    },
    created(){
      this.getlists();
    },
  };
</script>

<style scoped>
  .bohui{
    background: #F77272;
    color: white;
    font-size: 0.23rem;
    border-radius: 0.04rem;
    padding: 0.04rem 0.1rem 0.04rem 0.1rem;
    box-sizing: border-box;
    margin-right: 0.1rem;
  }
  .nocolor{
    color: #9a9a9a;
  }
  .firstcolors{
    font-weight: bold;
    color: #303030;
  }
  .nameben {
    width: 100%;
    height: 0.9rem;
    font-size: 0.34rem;
    background: #3F86FF;
    color: white;
    text-align: center;
    line-height: 0.9rem;
    position: fixed;
    bottom: 0;
  }
  .color{
    color: #303030;
  }
  .colors{
    color: #969696;
  }
  .flexbox{
    display: flex;
  }
  .flexs{
    flex: 1;
    justify-content: space-around;
    position: relative;
  }
  .heng{
    width: 0.5rem;
    height: 0.08rem;
    position: absolute;
    bottom: 0;
    background: rgba(63,134,255,1);
    margin-left: 50%;
    transform: translateX(-50%);
  }
  .stores{
    width: 100%;
    height: 0.82rem;
    line-height: 0.82rem;
    font-size: 0.3rem;
    text-align: center;
    background: white;
  }
  .color{
    background:  rgba(63,134,255,0.1);
  }
  .inputprice{
    margin-top: 0.2rem;
  }
  .inputprice span{
    font-size: 0.28rem;
    color: #727272;
    float: left;
    width: 2rem;
  }
  .inputprice input{
    width: 2.75rem;
    height: 0.52rem;
    border:solid 1px #EBEBEB;
    float: left;
    padding-left: 0.2rem;
  }
  .btnfow{
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    background: #F9F9F9;
    border-top: solid 1px #EEEEEE;
  }
  .btnfow div:nth-child(1){
    border-right: solid 1px #EEEEEE;
    box-sizing: border-box;
  }
  .btnfow div{
    width: 50%;
    line-height: 0.9rem;
    float: left;
    text-align: center;
  }
  .price-count{
    width: 5.98rem;
    height:4.32rem;
    background: white;
    border-radius:0.12rem;
    padding:0.3rem;
    padding-bottom: 0;
    margin-top: 1.45rem;
    margin-left: 50%;
    transform: translateX(-50%);
    position: absolute;
  }
  .pricebox{
    width: 100%;
    height: 100vh;
    position: fixed;
    top: 0;
    background: rgba(0,0,0,0.7);
  }
  .goods{
    width: 100%;
    height:0.77rem;
    line-height:0.79rem;
    padding-left:0.3rem;
    color: #727272;
    font-size: 0.3rem;
    box-sizing: border-box;
  }
  .mendian{
    font-size:0.28rem;
    color:#727272;
    margin-top: 0.13rem;
  }
  .zi-count{
    overflow: hidden;
    background: white;
    padding:0.3rem;
    box-sizing: border-box;
    border-bottom:solid #F1F3F4 0.01rem;
  }
  .count-box{
    overflow: hidden;
  }
  .lines{
    width: 100%;
    overflow: hidden;
    padding-top: 0.3rem;
  }
  .price{
    width:1.64rem;
    height:0.68rem;
    background:rgba(63,134,255,1);
    border-radius:0.06rem;
    color: white;
    font-size:.28rem;
    line-height: 0.68rem;
    text-align: center;
    float:right;
  }
  .titles{
    color: #303030;
    font-size: 0.3rem;
    float:left;
  }
</style>
