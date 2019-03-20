<template>
  <div>
    <!--暂无数据 -->
    <div class="hasnovalue" v-show="lengths==0">
      <img src="../../assets/jpgs/novalue.png"/>
      <p>暂无记录噢~~</p>
    </div>


    <!--已驳回-->
    <div class="count-box" v-for="item in list" v-show="item.status==4">
      <div class="zi-count">
        <div class="header">
          <span class="titles">{{item.billName}}</span>
          <span class="thtype" v-show="item.billType==1">常规市调</span>
          <span class="nothtype" v-show="item.billType==2">非常规市调</span>
          <span class="status s4">{{item.statusName}}</span>
        </div>
        <p class="danhao">市调单号：{{item.empBillCode}}</p>
        <p class="mendian">市调门店：{{item.opponents}}</p>
        <p class="mendian">共{{item.allCount}}个商品 已经完成：{{item.completeCount}}个</p>
        <p class="mendian" v-if="item.remark!=''&&item.remark!=undefined">驳回说明：{{item.remark}}</p>
        <p class="titmes">截止至{{item.endTime}}</p>
        <div class="hasfenpei" v-show="item.completeCount==0">
          <div class="morebtn btn" @click="maintain(item)">查看商品</div>
          <div class="firstbtn btn" @click="viewTtheImage(item)">查看图片</div>
        </div>
        <div class="hasfenpei" v-show="item.completeCount>0">
          <div class="morebtn btn" @click="submitebill(item)">提交</div>
          <div class="firstbtn btn" style="margin-left: 0.2rem" @click="maintain(item)">查看商品</div>
          <div class="firstbtn btn" @click="viewTtheImage(item)">查看图片</div>
        </div>
      </div>
    </div>

    <!--待处理-->
    <div class="count-box" v-for="item in list" v-show="item.status==1">
      <div class="zi-count">
        <div class="header">
          <span class="titles">{{item.billName}}</span>
          <span class="thtype" v-show="item.billType==1">常规市调</span>
          <span class="nothtype" v-show="item.billType==2">非常规市调</span>
          <span class="status s1">{{item.statusName}}</span>
        </div>
        <p class="danhao">市调单号：{{item.empBillCode}}</p>
        <p class="mendian">市调门店：{{item.opponents}}</p>
        <p class="mendian">共{{item.allCount}}个商品 已经完成：{{item.completeCount}}个</p>
        <p class="titmes">截止至{{item.endTime}}</p>
        <div class="hasfenpei" v-show="item.completeCount==0">
          <div class="morebtn btn" @click="maintain(item)">处理</div>
          <div class="firstbtn btn" @click="viewTtheImage(item)">上传图片</div>
        </div>
        <div class="hasfenpei"  v-show="!(item.completeCount==0)">
          <div class="morebtn btn" @click="submitebill(item)">提交</div>
          <div class="firstbtn btn" style="margin-left: 0.2rem" @click="maintain(item)">查看商品</div>
          <div class="firstbtn btn" @click="viewTtheImage(item)">查看图片</div>
        </div>
      </div>
    </div>

    <!--待审核-->
    <div class="count-box" v-for="item in list"  v-show="item.status==2">
      <div class="zi-count">
        <div class="header">
          <span class="titles">{{item.billName}}</span>
          <span class="thtype" v-show="item.billType==1">常规市调</span>
          <span class="nothtype" v-show="item.billType==2">非常规市调</span>
          <span class="status s2">{{item.statusName}}</span>
        </div>
        <p class="danhao">市调单号：{{item.empBillCode}}</p>
        <p class="mendian">市调门店：{{item.opponents}}</p>
        <p class="mendian">共{{item.allCount}}个商品 已经完成：{{item.completeCount}}个</p>
        <p class="titmes">截止至{{item.endTime}}</p>
        <div class="hasfenpei">
          <div class="morebtn btn" @click="productList(item)">查看商品</div>
          <div class="firstbtn btn" @click="viewTtheImage(item)">查看图片</div>
        </div>
      </div>
    </div>

    <!--已审核-->
    <div class="count-box"  v-for="item in list" v-show="item.status==3">
      <div class="zi-count">
        <div class="header">
          <span class="titles">{{item.billName}}</span>
          <span class="thtype" v-show="item.billType==1">常规市调</span>
          <span class="nothtype" v-show="item.billType==2">非常规市调</span>
          <span class="status s3">{{item.statusName}}</span>
        </div>
        <p class="danhao">市调单号：{{item.empBillCode}}</p>
        <p class="mendian">市调门店：{{item.opponents}}</p>
        <p class="mendian">共{{item.allCount}}个商品 已经完成：{{item.completeCount}}个</p>
        <p class="titmes">截止至{{item.endTime}}</p>
        <div class="hasfenpei">
          <div class="morebtn btn" @click="productList(item)">查看商品</div>
          <div class="firstbtn btn" @click="viewTtheImage(item)">查看图片</div>
        </div>
      </div>
    </div>



    <!--弹框（确认提交）-->
    <div class="messges" v-show="showsubmit">
      <div class="mybox">
        <p class="meggestitle">共{{itemnow.allCount}}个商品 已经完成：{{itemnow.completeCount}}个,未完成{{itemnow.allCount-itemnow.completeCount}}个,您是否确认提交？</p>
        <div class="messgesbtn">
          <div class="no" @click="nothis()">取消</div>
          <div class="yes" @click="yesthis()">确定</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Bus from '../bus.js'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"Pending",
    data() {
      return {
        lengths:0,
        showsubmit:false,
        count:1,
        complete:0,
        showcomplete:true,
        showmessages:false,
        list:[],
        itemnow:{},
      };
    },
    components: {Bus},
    methods: {
      nothis(){
          this.showsubmit=false;
      },
      yesthis(){
        var url="/empPricersh/submitByEmpBillCode";
        let data={};
        data.empBillCode=this.itemnow.empBillCode;
        data.personCode=JSON.parse(localStorage.getItem('infoess')).personCode;
        data.personName=JSON.parse(localStorage.getItem('infoess')).personName;
        this.$api.get(url, data, r => {
          if(!r.success){
            Bus.$emit('addMessgeImg',{code:3,info:r.msg});
            this.showsubmit=false;
            return;
          }
          this.getlist();
          this.showsubmit=false;
          Bus.$emit('addMessgeImg',{code:0,info:"提交成功"});
        }, f => {
          Bus.$emit('addMessgeImg',{code:3,info:f.msg});
          this.showsubmit=false;
        })
      },
      submitebill(item){
          this.itemnow=item;
        this.showsubmit=true;


      },
      productList(item){
        this.$router.push({
          name: "productList",
          params: {item:item}
        })
      },
      viewTtheImage(item){
        if(item.status=="1"||item.status=="4"){
          item.deletes=true;
        }
        else{
          item.deletes=false;
        }
        this.$router.push({
          name: "viewTtheImage",
          params: {item:item}
        })
      },
      maintain(item){
        localStorage.setItem("i",0);
        this.$router.push({
          name: "maintains",
          params: {item:item}
        })
      },
      getlist(){
        var url="/empPricersh/getListByRespPersonCode/"+JSON.parse(localStorage.getItem('infoess')).personCode+"";
        this.$api.get(url, {}, r => {
          if(!r.success){
            Bus.$emit('addMessgeImg',{code:3,info:r.msg});
            return;
          }
          this.list=r.data;
           for(var i=0;i<this.list.length;i++){
               if(this.list[i].statusName!="草稿"){
                   this.lengths++;
               }
           }
        }, f => {
          Bus.$emit('addMessgeImg',{code:3,info:f.msg});
        })
      }
    },
    created(){
        this.getlist();
    }
  };
</script>

<style scoped>
  .hasnovalue p{
    font-size: 0.28rem;
    color: #9a9a9a;
    text-align: center;
    margin-top: 0.5rem;
  }
  .hasnovalue img{
    width: 3.44rem;
    height: 2.38rem;
    margin: 0 auto;
    display: block;
    padding-top: 4.5rem;
  }
  .hasnovalue{
    width: 7.5rem;
    height: 9rem;
  }
  .messgesbtn div:nth-child(1){
    border-right:solid 1px #EEEEEE;
    color: #999999;
    box-sizing: border-box;
    border-bottom-left-radius:0.12rem;
  }
  .messgesbtn div:nth-child(2){
    color: #2479E5;
    border-bottom-right-radius:0.12rem;
  }
  .messgesbtn div{
    width: 50%;
    float: left;
    background:rgba(249,249,249,1);
    font-size: 0.3rem;
  }
  .messgesbtn div:nth-child(2){
    font-weight: bold;
  }
  .messgesbtn{
    width: 100%;
    height:0.9rem;
    line-height: 0.9rem;
    text-align: center;
    position: absolute;
    bottom: 0;
    border-bottom-left-radius:0.12rem;
    border-bottom-right-radius:0.12rem;
    border-top:solid 1px #EEEEEE;
  }
  .meggestitle{
    color: #303030;
    font-size: 0.3rem;
    padding-left: 0.58rem;
    padding-right: 0.58rem;
    box-sizing: border-box;
    text-align: center;
  }
  .mybox{
    width: 5.2rem;
    height:3.2rem;
    background: white;
    margin:0 auto;
    margin-top:4rem;
    border-radius:0.12rem;
    position: relative;
    padding-top:0.79rem;
    box-sizing: border-box;
  }
  .messges{
    width: 100%;
    height:100vh;
    position: fixed;
    top: 0;
    background: rgba(0,0,0,0.5);
  }
  .s1{
    color: #2479E5;
  }
  /*.s1{*/
    /*color: #5A00FF;*/
  /*}*/
  .s2{
    color: #0AC26A;
  }
  .s3{
    color: #FFA200;
  }
  .s4{
    color: #F43622;
  }
  .nothtype{
    padding-left:0.08rem;
    padding-right: 0.08rem;
    margin-top: 0.06rem;
    margin-left: 0.08rem;
    height:0.32rem;
    float:left;
    color: #2479E5;
    background:rgba(63,134,255,0.1);
    font-size: 0.24rem;
    display: block;
    line-height:0.32rem;
  }
  .morebtn{
    width: 1.64rem;
    background:rgba(63,134,255,1);
    height:0.68rem;
    margin-left: 0.2rem;
    color: white;
    border: solid 1px rgba(63,134,255,1);
  }
  .firstbtn{
    width: 1.64rem;
    height:0.68rem;
    border: solid 1px #C0C0C0;
  }
  .hasfenpei{
    overflow: hidden;
    margin-top: 0.3rem;
  }
  .firstbtn{
    float:right;
  }
  .morebtn{
    float:right;
  }
  .btn{
    width: 1.64rem;
    box-sizing: border-box;
    height:0.68rem;
    border-radius:0.06rem;
    font-size:.28rem;
    line-height: 0.68rem;
    text-align: center;
  }
  .titmes{
    font-size:0.28rem;
    color:#2479E5;
    margin-top: 0.1rem;
  }
  .mendian{
    font-size:0.28rem;
    color:rgba(114,114,114,1);
    margin-top: 0.1rem;
  }
  .danhao{
    font-size:0.28rem;
    color:rgba(114,114,114,1);
    margin-top: 0.28rem;
  }
  .status{
    font-size: 0.3rem;
    float: right;
  }
  .titles{
    color: #303030;
    font-size: 0.32rem;
    font-weight: bold;
    float:left;
  }
  .header{
    border-bottom: solid #F1F3F4 0.01rem;
    padding-bottom: 0.2rem;
    overflow: hidden;
  }
  .thtype{
    padding-left: 0.08rem;
    padding-right:  0.08rem;
    margin-top: 0.06rem;
    margin-left: 0.08rem;
    height:0.32rem;
    float:left;
    color: #0AC26A;
    background:rgba(0,207,127,0.1);
    font-size: 0.24rem;
    display: block;
    color:rgba(10,194,106,1);
    line-height:0.32rem;
  }
  .zi-count{
    overflow: hidden;
    background: white;
    padding:0.3rem;
    box-sizing: border-box;
    margin-top: 0.18rem;
  }
  .count-box{
    overflow: hidden;
  }
</style>
