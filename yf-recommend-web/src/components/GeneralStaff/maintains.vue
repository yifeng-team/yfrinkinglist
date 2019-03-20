<template>
  <div>

    <div class="stores flexbox" v-show="length>1">
      <div class="flexs" @click="changestore(item.ENTERPRISENAME,i)" :class="item.chack?'mycolor':'isnocolor'" v-for="item,i in list.competitionStore">
        {{item.ENTERPRISENAME}}
        <p class="heng" v-show="item.chack"></p>
      </div>
    </div>
    <div class="goods" v-for="item,i in list.allGooodNumber" v-show="i==indexnow">
      共{{item.COUNT}}个商品 已完成：<span style="color: #2479E5;">{{nowcompany}}</span>个
    </div>
    <div class="count-box">
      <div class="zi-count" v-for="item,i in list.rows" v-show="newstoreName==item.opponents">
        <div style="overflow: hidden;"><p class="titles">  <span class="bohui" v-show="item.status=='4'">驳回</span>{{item.goodName}}</p></div>
        <p class="mendian">商品条码：<span style="color:#303030">{{item.barCode}}</span></p>
        <p class="mendian" style="width: 50%;float: left" v-show="item.selling!=''">售价：<span style="color:#303030">{{item.selling}}</span></p>
        <p class="mendian" style="width: 50%;float: left" v-show="item.memberPrice!=''">会员价：<span style="color:#303030">{{item.memberPrice}}</span></p>
        <div class="lines"> <div @click="MaintenancePrice(item,i)" class="price">维护单价</div></div>
      </div>
    </div>

    <!--维护单价输入框-->
    <div class="pricebox" v-show="showprice">
      <div class="price-count">
        <div style="overflow: hidden"><p class="titles">{{nowitem.goodName}}</p></div>
        <p class="mendian">商品条码：<span style="color:#303030">{{nowitem.barCode}}</span></p>
        <div class="inputprice">
          <span>售价（元）</span>
          <div  @click="onece(1)" :class="changecount==1?'color':'white'"  v-model="selling"> {{selling}}</div>
        </div>
        <div class="inputprice">
          <span>会员价（元）</span>
          <div  @click="onece(2)" :class="changecount==2?'color':'white'"  v-model="memberPrice">{{memberPrice}}</div>
        </div>
        <div class="btnfow">
          <div @click="no">取消</div>
          <div style="color: #2479E5;font-weight: bold" @click="yes">确定</div>
        </div>
      </div>
      <keyMou @callBack="callBacks" :showKeys="keyvalue" ></keyMou>
    </div>
    <div class="nameben" v-show="!showprice" @click="success()">完成</div>
  </div>
</template>
<script>
  import keyMou from "../com/notice/keyMou"
  import Bus from '../bus.js'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"maintains",
    data() {
      return {
        changecount:1,
        selling:"0.00",
        memberPrice:"0.00",
        showprice:false,
        list:{},
        nowitem:{},
        newstoreName:"",
        length:0,
        indexnow:0,
        nowcompany:0,
        keyvalue:true,
        goodsInfo:{quantity:0,xtQuantity:0},
        upDateIndex:true,
        money:"",
      };
    },
    components: {
      keyMou,
      Bus,
    },
    filters:{
    },
    methods: {
      callBacks(obj){
    if(obj.value=='.'){
        this._handleDecimalPoint();
        return;
    }
        if(obj.value=='d'){
          this._handleDeleteKey();
          return;
        }
        if(typeof(obj.value)!="string"&&obj.value==1){
            this.changecount=2;
          this.money="";
            return
        }
        if(typeof(obj.value)!="string"&&obj.value==0){
          this.yes();
          return
        }
     this._handleNumberKey(obj.value);
      },
      //处理删除键
      _handleDeleteKey() {
        let S = this.money;
        //如果没有输入，直接返回
        if (!S.length) return false;
        //否则删除最后一个
        this.money = S.substring(0, S.length - 1);
        this.isselling();
      },
      //处理数字
      _handleNumberKey(num) {
        let S = this.money;

        //如果有小数点且小数点位数不小于2
        if ( S.indexOf('.') > -1 && S.substring(S.indexOf('.') + 1).length < 2)
          this.money = S + num;


        //没有小数点
        if (!(S.indexOf('.') > -1)) {
          //如果第一位是0，只能输入小数点
          if (num == 0 && S.length == 0)
            this.money = '0.';

          else {
            if (S.length && Number(S.charAt(0)) === 0) return;
            this.money = S + num;
          }
        }
        this.isselling();
      },
      //处理小数点
      _handleDecimalPoint() {
        //如果包含小数点，直接返回
        if (this.money.indexOf('.') > -1) return false;
        //如果小数点是第一位，补0
        if (!this.money.length) {
          this.money = '0.';
        }

        //如果不是，添加一个小数点
        else {
          this.money = this.money + '.';
        }
        this.isselling();
      },
      //判断是否为售价
      isselling(){
         if(this.changecount==1){
          this.selling=this.money;
         }
         else{
           this.memberPrice=this.money;
         }
      },
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
            localStorage.setItem("i",index);
           this.changecount=1;
            this.indexnow=index;
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
          this.findvalue(index);
        },
//    维护单价完成之后完成按钮
      success(){
        this.$router.go(-1);
      },
        no(){
          this.showprice=false;
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
            this.money="";
            var objt={};
            objt=this.nowitem;
            objt.selling=this.selling;
            objt.memberPrice=this.memberPrice;
            this.$set(this.list.rows,this.nowitem.index,objt);

            this.selling=0;
            this.memberPrice=0;
            this.getlists();
          }, f => {
            Bus.$emit('addMessgeImg',{code:3,info:f.msg});
          })
            this.showprice=false;
        },
      onece(value){
        this.changecount=value;
        this.money="";
      },
      _confirmEvent(res){
        if(this.changecount==1){
          this.selling=res;
          this.changecount=2;
        }
        else{
          this.memberPrice=res;
          this.yes();
        }
      },
      MaintenancePrice(item,index){
        this.changecount=1;
          this.nowitem=item;
          this.nowitem.index=index;
          this.showprice=true;
          this.selling=item.selling;
          this.memberPrice=item.memberPrice;
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

          var index=localStorage.getItem("i");
          let objs={};
          objs=this.list.competitionStore[index];
          objs.chack=true;
          this.$set(this.list.competitionStore,index,objs);
          this.newstoreName=r.data.competitionStore[index].ENTERPRISENAME;

          this.length=this.list.competitionStore.length;
          this.findvalue(index);


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
  .mycolor{
    font-weight: bold;
    color: #303030;
  }
  .isnocolor{
    color: #9a9a9a;
  }
  input{
    font-size: 0.28rem;
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
    background: #3F86FF;
    margin-left: 50%;
    transform: translateX(-50%);
  }
  .stores{
    width: 100%;
    height: 0.82rem;
    line-height: 0.82rem;
    font-size: 0.3rem;
    text-align: center;
    position: fixed;
    top: 0;
    background: white;
  }
  .color{
    background:  rgba(63,134,255,0.1);
  }
  .inputprice{
    margin-top: 0.2rem;
    overflow: hidden;
  }
  .inputprice span{
   font-size: 0.28rem;
    color: #727272;
    float: left;
    width: 2rem;
  }
  .inputprice div{
    width: 2.75rem;
    height: 0.52rem;
    border:solid 1px #EBEBEB;
    float: left;
    padding-left: 0.2rem;
    cursor: pointer;
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
    border-bottom-left-radius: 0.12rem;
  }
  .btnfow div{
    width: 50%;
    line-height: 0.9rem;
    float: left;
    text-align: center;
  }
  .price-count{
    width: 5.98rem;
    height:5rem;
    background: white;
    border-radius:0.12rem;
    padding:0.3rem;
    padding-bottom: 0;
    margin-top: 0.8rem;
    margin-left: 50%;
    transform: translateX(-50%);
    position: absolute;
    box-sizing: border-box;
    overflow: hidden;
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
    position: fixed;
    top: 0.82rem;
    background: #f8f8f9
  }
  .mendian{
    font-size:0.28rem;
    color:#727272;
    padding-top: 0.2rem;
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
    margin-bottom: 0.9rem;
    margin-top: 1.59rem;
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
    line-height: 0.72rem;
    text-align: center;
    float:right;
  }
  .titles{
    color: #303030;
    font-size: 0.3rem;
    float:left;
    overflow: hidden;
  }
</style>
