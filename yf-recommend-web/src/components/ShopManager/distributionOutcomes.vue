<template>
  <div>
    <div class="box">
      <div><div class="header"></div>
        <span class="name">{{list.respPersonName}}</span>
        <span class="number">分配商品:{{list.goodsCount}}个</span>
        <!--<span class="jiantou"></span>-->
      </div>
      <p class="shidiao">市调单号：{{list.empBillCode}}</p>
      <p class="shidiao">市调门店：{{list.opponents}}</p>
    </div>
    <div class="bigbox">
      <div class="box-header" v-if="myitem.status=='5'">
        <div @click="allchacks()" style="float: left">
          <span class="nogou margin" @ v-show="!allchack"></span>
          <span class="gou margin" v-show="allchack"></span>
          <span class="all">全选</span>
        </div>
        <div class="btns" @click="giveother">调给其他同事</div>
      </div>
      <div class="count-box">
        <div class="count" v-for="item,i in row"  @click="chack(item,i)">
          <div>
            <div class="title padding" :class="myitem.status=='5'?'toright':'roleft'">{{item.goodsName}}</div>
            <span v-if="myitem.status=='5'" class="nogou margin" v-show="!item.chack"></span>
            <span v-if="myitem.status=='5'" class="gou margin" v-show="item.chack"></span>
            <div class="hongkuai"></div>
          </div>
          <p class="shidiao" :class="myitem.status=='5'?'marginleft':''">商品条码：<span style="color: #303030">{{item.mainBarCode}}</span></p>
        </div>
      </div>
    </div>

    <!--同事名单-->
    <div class="showlist" v-show="showlist">
        <div class="list-title">请选择责任人</div>
        <div class="overflow">
          <ul>
            <li v-for="item,i in namelist"  v-show="item.personCode!=list.respPersoncode" @click="namechack(item,i)">{{item.employeeName}}<span class="dagou" v-show="item.chack"></span></li>
          </ul>
        </div>
      <div class="nameben" @click="yeschack()">确定</div>
    </div>
  </div>
</template>
<script>
  //import { MessageBox } from "mint-ui";
  import Bus from '../bus.js'
  export default {
    name:"distributionOutcomes",
    data() {
      return {
        myitem:{},
        showlist:false,
        allchack:false,
        namelist:[],
        row:[],
        personcode:"",
      };
    },
    components: {Bus},
    methods: {
        //确定选择此人
        yeschack(){
         var nowlist="";
         var goodCode="";
            //获取选中的商品列表将该条取出uuid
          for(var i=0;i<this.row.length;i++){
              if(this.row[i].chack){
                  nowlist=this.row[i].empBillCode;
                  goodCode+=this.row[i].goodCode+",";
              }
          }
          goodCode=goodCode.substring(0,goodCode.lastIndexOf(","));
          var personCode="";
          //获取所选择的人
          for(var i=0;i<this.namelist.length;i++){
            if(this.namelist[i].chack){
              personCode=this.namelist[i].personCode;
            }
          }
          var url="/empPricershGoods/transferToOther";
          let data={
            "personCode": JSON.parse(localStorage.getItem('infoess')).personCode,
            "targetPersonCode": personCode,
            "empBillCode": nowlist,
            "goodCodes":goodCode,
          }
          this.$api.post(url, data, r => {
            if(!r.success){
              Bus.$emit('addMessgeImg',{code:3,info:r.msg});
              this.$router.go(-2);
            }
            Bus.$emit('addMessgeImg',{code:0,info:"操作成功"});
            this.$router.go(-1);
            this.showlist=false;
          }, f => {
            Bus.$emit('addMessgeImg',{code:3,info:f.msg});
          })
        },
      //名字列表选择
      namechack(item,index){
        for(var i=0;i<this.namelist.length;i++){
          var obj=this.namelist[i];
          obj.chack=false;
          this.$set(this.namelist,i,obj);
        }
        let ob=item;
        ob.chack=!ob.chack;
        this.$set(this.namelist,index,ob);
      },
      //全选按钮
      allchacks(){
          this.allchack=!this.allchack;
            for(var i=0;i<this.row.length;i++){
              var obj=this.row[i];
              obj.chack=this.allchack;
              this.$set(this.row,i,obj);
            }
      },
      //子单勾选功能
      chack(item,index){
          let obj=item;
          obj.chack=!item.chack;
          this.$set(this.row,index,obj);
          var isallchack=true;
        for(var i=0;i<this.row.length;i++){
            if(!this.row[i].chack){
              this.allchack=false;
              isallchack=false;
                break;
            }
        }
       if(isallchack){
         this.allchack=true;
       }

      },
      //选择其他同事按钮事件
      giveother(){
          //判断是否一个都没选中
        var isallchack=false;
        for(var i=0;i<this.row.length;i++){
          if(this.row[i].chack){//全部为false表示一个都没选中，如果有被选中的就将
            isallchack=true;
            break;
          }
        }
        if(isallchack){//说明有被选中的
          this.showlist=true;
        }
        else{//说明一个都没有选中
          Bus.$emit('addMessgeImg',{code:3,info:'请选中至少一个商品'});
        }

      },
      getlist(){
        var item=this.$route.params.item;
        var url="/empPricershGoods/getEmpPricershGoods/"+item.empBillCode+"";
        this.$api.get(url, {}, r => {
            if(!r.success){
              Bus.$emit('addMessgeImg',{code:3,info:r.msg});
              this.$router.go(-2);
            }
            this.row=r.data;
          for(var i=0;i<this.row.length;i++){
            var obj=this.row[i];
            obj.chack=false;
            this.$set(this.row,i,obj);
          }
        }, f => {
          Bus.$emit('addMessgeImg',{code:3,info:f.msg});
        })

        var urls="/person/getStaffByStoreCode";
        let data={};
        data.storeCode=JSON.parse(localStorage.getItem('infoess')).departmentCode;
        this.$api.get(urls, data, r => {
          if(!r.success){
            Bus.$emit('addMessgeImg',{code:3,info:r.msg});
            this.$router.go(-2);
          }
          this.namelist=r.data;
          for(var i=0;i<this.namelist.length;i++){
            var obj=this.namelist[i];
            obj.chack=false;
            this.$set(this.namelist,i,obj);
          }
        }, f => {
        })

       this.myitem=JSON.parse(localStorage.getItem("item"));
      },
    },
    created(){
        this.getlist();
      this.list=this.$route.params.item;
      for(var i=0;i<this.namelist.length;i++){
        var obj=this.namelist[i];
        obj.chack=false;
        this.$set(this.namelist,i,obj);
      }
      this.personcode=JSON.parse(localStorage.getItem('infoess')).personCode
    },
  };
</script>

<style scoped>
  .nameben{
    width: 7.5rem;
    height: 0.9rem;
    font-size: 0.34rem;
    background: #3F86FF;
    color: white;
    text-align: center;
    line-height: 0.9rem;
    position: fixed;
    bottom: 0;
  }
  .dagou{
    width: 0.34rem;
    height:0.29rem;
    background:url("../../assets/jpgs/shidiao.png") no-repeat;
    background-size: 100% 100%;
    display: block;
    float:right;
    margin-top: 0.25rem;
    margin-right: 0.3rem;
  }
  .overflow ul li{
    width: 7.5rem;
    height:0.87rem;
    background: white;
    border-bottom:solid 1px #f1f3f4;
    font-size: 0.3rem;
    color: #303030;
    padding-left: 0.3rem;
    line-height: 0.87rem;
    box-sizing: border-box;
  }
  .overflow{
    overflow-y: auto;
    height: 80vh;
  }
  .list-title{
    font-size:0.28rem;
    color:rgba(154,154,154,1);
    line-height:1rem;
    text-align: center;
    background: #f8f8f9;
  }
  .showlist{
    width: 7.5rem;
    min-height:100vh;
    position: fixed;
    top: 0;
    background: white;
  }
  .count{
    padding:0.25rem;
    padding-top: 0;
    box-sizing: border-box;
    border-bottom: solid 1px #F1F3F4;
  }
  .marginleft{
    margin-left: 0.78rem;
  }
  .toright{
    float:right;
  }
  .toleft{
    float:left;
  }
  .title{
    font-size: 0.3rem;
    color: #303030;
    width: 6.34rem;
    overflow: hidden;
  }
  .box-header{
    width: 100%;
    height:0.96rem;
    padding-left:0.25rem;
    padding-right:0.25rem;
    box-sizing: border-box;
  }
  .all{
    font-size: 0.28rem;
    color: #727272;
    float: left;
    margin-left:0.15rem;
    line-height: 0.96rem;
  }
  .btns{
    width: 1.94rem;
    height:0.6rem;
    border:#3F86FF solid 1px;
    color: #3F86FF;
    line-height: 0.6rem;
    text-align: center;
    font-size: 0.28rem;
    border-radius: 0.06rem;
    float: right;
    margin-top: 0.15rem;
  }
  .margin{
    margin-top: 0.3rem;
  }
  .padding{
    padding-top: 0.3rem;
  }
  .hongkuai{
    width: 0.5rem;
    height: 1rem;
    position: absolute;
  }
  .nogou{
    width: 0.34rem;
    height:0.34rem;
    background: url("../../assets/jpgs/xiao.png") no-repeat;
    background-size: 100% 100%;
    display: block;
    float:left;
  }
  .gou{
    width: 0.34rem;
    height:0.34rem;
    background: url("../../assets/jpgs/gou.png") no-repeat;
    background-size: 100% 100%;
    display: block;
    float:left;
  }
  .bigbox{
    width: 100%;
    background: white;
    margin-top: 0.18rem;
    overflow: hidden;
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
    width: 100%;
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
    background: white;
    margin-top: 0.18rem;
    padding: 0.3rem;
    box-sizing: border-box;
  }
  .header{
    width: 0.7rem;
    height:0.7rem;
    background: url("../../assets/jpgs/header.png");
    background-size: 100% 100%;
    float:left;
  }
</style>
