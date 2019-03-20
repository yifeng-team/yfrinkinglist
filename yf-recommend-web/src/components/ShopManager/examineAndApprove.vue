<template>
  <div>
    <div class="allbox" v-show="lastitem.statusName=='待审核'">
      <div>
        <div class="lefts" @click="chackall()">
          <span class="nogou margin mar"  v-show="!allchack"></span>
          <span class="gou margin mar" v-show="allchack"></span>
          <span class="quanxuan">全选</span>
        </div>
        <div class="hasfenpei">
          <div class="morebtn btn"  @click="agree()">同意</div>
          <div class="firstbtn btn" @click="bohui()">驳回</div>
        </div>
      </div>
    </div>
    <div class="stores flexbox" v-show="length>1">
      <div class="flexs" @click="changestore(item.ENTERPRISENAME,i)" v-for="item,i in list.competitionStore">
        {{item.ENTERPRISENAME}}
        <p class="heng" v-show="item.chack"></p>
      </div>
    </div>
    <div class="goods" v-for="item,i in list.allGooodNumber" v-show="i==indexnow">
      共{{item.COUNT}}个商品 已完成：<span style="color: #2479E5;">{{nowcompany}}</span>个
    </div>
    <div class="count-box">
      <div class="zi-count" v-for="item,i in list.rows" v-show="newstoreName==item.opponents" @click="changechack(item,i)">
        <span class="nogou margin"  v-show="!item.chack&&lastitem.statusName=='待审核'"></span>
        <span class="gou margin" v-show="item.chack&&lastitem.statusName=='待审核'"></span>
        <div style="overflow: hidden"><p class="titles">{{item.goodName}}</p></div>
        <p class="mendian">商品条码：<span style="color:#303030">{{item.barCode}}</span></p>
        <p class="mendian" style="width: 50%;float: left" v-show="item.selling!=''">售价：<span style="color:#303030">{{item.selling}}</span></p>
        <p class="mendian" style="width: 50%;float: left;" v-show="item.memberPrice!=''">会员价：<span style="color:#303030">{{item.memberPrice}}</span></p>
      </div>
    </div>

    <!--驳回-->
    <div class="bohui" v-show="bohuis">
      <div class="bigbox">
         <div class="kuang">
           <textarea class="text" maxlength="100" v-model="myword" :placeholder="liyou"></textarea>
           <div class="number">
             <span class="qian" :class="myword.length>0?'hasword':'noword'">{{myword.length}}</span>
             <span class="hou">/100</span>
           </div>
         </div>
        <div class="liyoubtn flexbox">
          <div class="flexs quxiao" @click="bohui()">取消</div>
          <div class="flexs quding" @click="queding()">确定</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import calculation from "../com/notice/calculation"
  import Bus from '../bus.js'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"examineAndApprove",
    data() {
      return {
        liyou:"请输入驳回理由",
        myword:"",
        bohuis:false,
        allchack:false,
        changecount:1,
        selling:0,
        memberPrice:0,
        showprice:false,
        list:{},
        nowitem:{},
        newstoreName:"",
        length:0,
        isbohui:0,
        indexnow:0,
        nowcompany:0,
        lastitem:{},
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
      queding(){
          var uuid="";
          for(var i=0;i<this.list.rows.length;i++){
              if(this.list.rows[i].chack){
                  uuid+=this.list.rows[i].uuid+",";
              }
          }
          if(uuid!="") {
            uuid=uuid.substring(0, uuid.lastIndexOf(","));
          }
          else{
            if(this.isbohui=="4") {
              Bus.$emit('addMessgeImg', {code: 1, info: "请选择要处理的商品"});
              return;
            }
            else{
              for(var i=0;i<this.list.rows.length;i++){
                  uuid+=this.list.rows[i].uuid+",";
              }
            }
          }

          if(this.isbohui=="4"&&this.myword==""){
            Bus.$emit('addMessgeImg',{code:1,info:"请输入驳回理由"});
            return;
          }
        this.lookimg=[];
        let data={
          "empBillCode": this.$route.params.item.empBillCode,
          "personCode": JSON.parse(localStorage.getItem('infoess')).personCode,
          "remark": this.myword,
          "status": this.isbohui,//审核3，驳回4
          "uuids": uuid
        }
        this.$api.post("/empPricershGoods/auditGoods",data, r => {
          this.bohuis=false;
          this.$router.go(-2);
        }, f => {
          Bus.$emit('addMessgeImg',{code:1,info:f.msg});
        })
      },
      agree(){
        this.isbohui="3";
//        this.liyou="请输入审批理由";
//        this.bohuis=true;
      this.queding();
      },
        bohui(){
          this.isbohui="4";
            this.bohuis=!this.bohuis;
        },
      chackall(){
          this.allchack=!this.allchack;
          for(var index=0;index<this.list.rows.length;index++) {
            var obj = this.list.rows[index];
            obj.chack = this.allchack;
            this.$set(this.list.rows, index, obj);
          }
      },
      changechack(item,index){
        var obj=this.list.rows[index];
        obj.chack=!this.list.rows[index].chack;
        this.$set(this.list.rows,index,obj);

        var rows=true;
        for(var i=0;i<this.list.rows.length;i++){
            if(this.list.rows[i].chack==false){
              rows=false;
            }
        }
        if(rows){
            this.allchack=true;
        }
        else{
            this.allchack=false;
        }
      },
//      选项卡切换
      changestore(name,index){
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

        this.showprice=false;



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
      MaintenancePrice(item,index){
        this.nowitem=item;
        this.nowitem.index=index;
        this.showprice=true;
      },
      getlists(){
        var item=this.$route.params.item;
        this.lastitem=item;
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


          for(var i=0;i<r.data.rows.length;i++){
            obj=r.data.rows[i];
            obj.chack=false;
            r.data.rows[i]=obj;
          }
           this.list=r.data;

          this.length=this.list.competitionStore.length;
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
  .noword{
    color: #B7B7B7;
  }
  .hasword{
    color: rgba(63,134,255,1);
  }
  .hou{
    color: #B7B7B7;
  }
  .number{
    position: absolute;
    bottom: 1.2rem;
    right: 0.25rem;
    font-size: 0.28rem;
  }
  .quxiao{
    color:rgba(63,134,255,1);
  }
  .quding{
    background: rgba(63,134,255,1);
    color: white;
  }
  .liyoubtn{
    width: 7.5rem;
    height: 0.88rem;
    font-size: 0.32rem;
    text-align: center;
    line-height: 0.88rem;
    background: white;
    border-top: rgba(63,134,255,1) solid 0.01rem;
  }
  .text{
    width: 6.9rem;
    height: 3rem;
    margin: 0 auto;
    display: block;
    border: none;
    padding: 0.25rem;
    color: #303030;
    font-size: 0.28rem;
  }
  .bigbox{
    overflow: hidden;
    position: absolute;
    bottom: 0;
  }
  .kuang{
    width: 7.5rem;
    height: 3.8rem;
    background: white;
  }
  .bohui{
    width: 7.5rem;
    height: 100vh;
    position: fixed;
    top: 0;
    background: rgba(0,0,0,0.5);
  }
  .morebtn{
    width: 1.64rem;
    background:rgba(63,134,255,1);
    height:0.68rem;
    margin-left: 0.2rem;
    color: white;
  }
  .firstbtn{
    width: 1.64rem;
    border: solid 1px #C0C0C0;
    height:0.68rem;
  }
  .lefts{
  float: left;
    position: relative;
  }
  .btn{
    width: 1.64rem;
    box-sizing: border-box;
    height:0.68rem;
    border-radius:0.06rem;
    font-size:.28rem;
    text-align: center;
    line-height: 0.7rem;
  }
  .hasfenpei{
    overflow: hidden;
    width: 4rem;
    float: right;
    margin-top: 0.15rem;
    margin-right: 0.25rem;
  }
  .firstbtn{
    float:right;
  }
  .morebtn{
    float:right;
  }
  .mar{
    margin-top: 0.3rem;
  }
  .quanxuan{
    line-height: 0.96rem;
    font-size: 0.3rem;
    color: #999999;
    margin-left: 0.8rem;
  }
  .allbox{
    width: 100%;
    height: 0.96rem;
    background: white;
  }
  .nogou{
    width: 0.34rem;
    height:0.34rem;
    background: url("../../assets/jpgs/xiao.png") no-repeat;
    background-size: 100% 100%;
    display: block;
    float:left;
    position: absolute;
    left: 0.3rem;
  }
  .gou{
    width: 0.34rem;
    height:0.34rem;
    background: url("../../assets/jpgs/gou.png") no-repeat;
    background-size: 100% 100%;
    display: block;
    float:left;
    position: absolute;
    left: 0.3rem;
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
  }
  .mendian{
    font-size:0.28rem;
    color:#727272;
    margin-top: 0.2rem;
  }
  .zi-count{
    overflow: hidden;
    background: white;
    padding:0.3rem;
    box-sizing: border-box;
    border-bottom:solid #F1F3F4 0.01rem;
    padding-left: 0.9rem;
    position: relative;
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
    line-height: 0.72rem;
    text-align: center;
    float:right;
  }
  .titles{
    color: #303030;
    font-size: 0.3rem;
    float:left;
  }
</style>
