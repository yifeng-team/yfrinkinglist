<template>
  <div>
     <div class="flexbox bigbox">
       <div class="flexs" :class="count==1?'color':'nocolor'" @click="changecount(1)">待分配<p v-show="count==1"></p><!--<span class="number">1</span>--></div>
       <div class="flexs" :class="count==2?'color':'nocolor'" @click="changecount(2)">已分配<p v-show="count==2"></p><!--<span class="number">1</span>--></div>
       <div class="flexs" :class="count==3?'color':'nocolor'" @click="changecount(3)">待审核<p v-show="count==3"></p><!--<span class="number">1</span>--></div>
       <div class="flexs" :class="count==4?'color':'nocolor'" @click="changecount(4)">已审核<p v-show="count==4"></p><!--<span class="number">1</span>--></div>
       <div class="flexs" :class="count==5?'color':'nocolor'" @click="changecount(5)">已驳回<p v-show="count==5"></p><!--<span class="number">1</span>--></div>
     </div>

    <!--状态数据-->
    <div class="count-bigbox">
      <!--待分配-->
      <div class="count-box"  v-show="count==1">
        <div class="zi-count" v-for="item in list" v-show="item.status=='0'||item.status=='5'">
          <div class="header">
             <span class="titles">{{item.billName}}</span>
            <span class="thtype" v-show="item.marketresearchplanType=='1'">常规市调</span>
            <span class="nothtype" v-show="item.marketresearchplanType=='2'">非常规市调</span>
             <span class="status s0">{{item.statusShow}}</span>
          </div>
          <p class="danhao">市调单号：{{item.billCode}}</p>
          <p class="mendian">市调门店：{{item.opponents}}</p>
          <p class="mendian">共{{item.goodsCount}}个商品 已经分配：<span style="color: #2479E5">{{item.distributedGoodsCount}}</span>个</p>
          <p class="titmes">截止至 {{item.endtime.substring(0,item.endtime.indexOf(" "))}}</p>
          <div class="hasfenpei"  v-show="item.status==5">
            <div class="morebtn btn" @click="submitgo(item)">提交</div>
            <div class="firstbtn btn" @click="distributionOutcomesList(item)">查看结果</div>
          </div>
         <div class="lines"  v-show="item.status==0"> <div @click="average(item)" class="fenbtn">平均分配</div></div>
        </div>
      </div>

      <!--已分配-->
      <div class="count-box"  v-show="count==2">
        <div class="zi-count" v-for="item in list" v-show="item.status=='3'" @click="distributionOutcomesList(item)">
          <div class="header">
            <span class="titles">{{item.billName}}</span>
            <span class="thtype" v-show="item.marketresearchplanType=='1'">常规市调</span>
            <span class="nothtype" v-show="item.marketresearchplanType=='2'">非常规市调</span>
            <span class="status s0">{{item.statusShow}}</span>
          </div>
          <p class="danhao">市调单号：{{item.billCode}}</p>
          <p class="mendian">市调门店：：{{item.opponents}}</p>
          <p class="mendian">共{{item.goodsCount}}个商品 已经分配：<span style="color: #2479E5">{{item.distributedGoodsCount}}</span>个</p>
          <p class="titmes">截止至 {{item.endtime.substring(0,item.endtime.indexOf(" "))}}</p>
        </div>
      </div>

      <!--待审核-->
      <div class="count-box"  v-show="count==3">
        <div class="zi-count"  v-for="item in list" v-show="item.status=='1'">
          <div class="header">
            <span class="titles">{{item.billName}}</span>
            <span class="thtype" v-show="item.marketresearchplanType=='1'">常规市调</span>
            <span class="nothtype" v-show="item.marketresearchplanType=='2'">非常规市调</span>
            <span class="status s0">{{item.statusShow}}</span>
          </div>
          <p class="danhao">市调单号：{{item.billCode}}</p>
          <p class="mendian">市调门店：{{item.opponents}}</p>
          <p class="mendian">共{{item.goodsCount}}个商品 已经分配：<span style="color: #2479E5">{{item.distributedGoodsCount}}</span>个</p>
          <p class="titmes">截止至 {{item.endtime.substring(0,item.endtime.indexOf(" "))}}</p>
          <div class="hasfenpei">
            <div class="morebtn btn" @click="distributionOutcomesList(item)">审核</div>
            <div class="firstbtn btn"  @click="lookAllImages(item)">查看图片</div>
          </div>
        </div>
      </div>

      <!--已审核-->
      <div class="count-box"  v-show="count==4">
        <div class="zi-count"  v-for="item in list" v-show="item.status=='2'||item.status=='6'">
          <div class="header">
            <span class="titles">{{item.billName}}</span>
            <span class="thtype" v-show="item.marketresearchplanType=='1'">常规市调</span>
            <span class="nothtype" v-show="item.marketresearchplanType=='2'">非常规市调</span>
            <span class="status s0">{{item.statusShow}}</span>
          </div>
          <p class="danhao">市调单号：{{item.billCode}}</p>
          <p class="mendian">市调门店：{{item.opponents}}</p>
          <p class="mendian">共{{item.goodsCount}}个商品 已经分配：<span style="color: #2479E5">{{item.distributedGoodsCount}}</span>个</p>
          <p class="titmes">截止至 {{item.endtime.substring(0,item.endtime.indexOf(" "))}}</p>
          <div class="hasfenpei">
            <div class="morebtn btn"  @click="distributionOutcomesList(item)">查看</div>
            <div class="firstbtn btn" @click="lookAllImages(item)">查看图片</div>
          </div>
        </div>
      </div>

      <!--已驳回-->
      <div class="count-box"  v-show="count==5">
        <div class="zi-count"  v-for="item in list" v-show="item.status=='4'">
          <div class="header">
            <span class="titles">{{item.billName}}</span>
            <span class="thtype" v-show="item.marketresearchplanType=='1'">常规市调</span>
            <span class="nothtype" v-show="item.marketresearchplanType=='2'">非常规市调</span>
            <span class="status s0">{{item.statusShow}}</span>
          </div>
          <p class="danhao">市调单号：{{item.billCode}}</p>
          <p class="mendian">市调门店：{{item.opponents}}</p>
          <p class="mendian">共{{item.goodsCount}}个商品 已经分配：<span style="color: #2479E5">{{item.distributedGoodsCount}}</span>个</p>
          <p class="titmes">截止至 {{item.endtime.substring(0,item.endtime.indexOf(" "))}}</p>
          <div class="hasfenpei">
            <div class="morebtn btn"  @click="distributionOutcomesList(item)">查看</div>
            <div class="firstbtn btn" @click="lookAllImages(item)">查看图片</div>
          </div>
        </div>
      </div>
    </div>

    <div class="hasnovalue" v-show="list.length==0">
        <img src="../../assets/jpgs/novalue.png"/>
        <p>暂无记录噢~~</p>
    </div>
    <!--弹框（确认分配）-->
    <div class="messges" v-show="showmessages">
        <div class="mybox">
          <p class="meggestitle">是否确认将当前商品平均分配给员工？</p>
          <div class="messgesbtn">
            <div class="no" @click="no()">取消</div>
            <div class="yes" @click="yes()">确定</div>
          </div>
        </div>
    </div>

    <!--弹框（确认提交）-->
    <div class="messges" v-show="showsubmit">
      <div class="mybox">
        <p class="meggestitle">是否确认提交该单据？</p>
        <div class="messgesbtn">
          <div class="no" @click="nothis()">取消</div>
          <div class="yes" @click="yesthis()">确定</div>
        </div>
      </div>
    </div>


    <!--提交成功之后的提示-->
    <div class="sucssuce" v-show="showsu">
      <div class="mes"></div>
      <p class="boxzi">分配成功</p>
      <p class="xiaozi">当前是调单已成功分配</p>
      <div class="backbtn" @click="back()">返回</div>
    </div>
  </div>
</template>
<script>
  import Bus from '../bus.js'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"Marklist",
    data() {
      return {
        showsu:false,
        count:1,
        complete:0,
        showcomplete:true,
        showmessages:false,
        showsubmit:false,
        list:[],
        nowlist:{},
        submitelist:[],
      };
    },
    components: {Bus},
    methods: {
      lookAllImages(item){
        this.$router.push({
          name: "lookAllImages",
          params: {item:item}
        })
      },
      nothis(){
          this.showsubmit=false;
      },
      //分配成功的页面弹出，将隐藏并刷新该页面
      back(){
          this.showsu=false;
      },
      //确认提交按钮
      yesthis(){
        var item=this.submitelist;
//        let data={};
//        data.billCode=item.billCode;
//        data.personCode=JSON.parse(localStorage.getItem('infoess')).personCode;
        var url="/areaPricersh/submitByBillCode/"+item.billCode+"/"+JSON.parse(localStorage.getItem('infoess')).personCode;
        this.$api.get(url, {}, r => {
          if(!r.success){
            Bus.$emit('addMessgeImg',{code:3,info:r.msg});
            this.showsubmit=false
            return;
          }
          this.list=[];
          this.getlist(0,0);
          this.getlist(0,5);
          Bus.$emit('addMessgeImg',{code:0,info:"提交成功"});
          this.showsu=true;
          this.showsubmit=false;
        }, f => {
          Bus.$emit('addMessgeImg',{code:3,info:f.msg});
          this.showsubmit=false
        })
      },
      //提交单据按钮
      submitgo(item){
          this.submitelist=item;
           this.showsubmit=true;
      },
      no(){
        this.showcomplete=false;
        this.showmessages=false;
      },

      //确认平均分配的弹框按钮
        yes(){
          this.showcomplete=false;
          this.showmessages=false;
          var url="/areaPricersh/distributeGoods";
          let data={};
          data.billCode=this.nowlist.billCode;
          data.distributePersonCode=this.nowlist.respPersoncode;
          this.$api.post(url, data, r => {
            if(!r.success){
              Bus.$emit('addMessgeImg',{code:3,info:r.msg});
              return;
            }
            this.list=[];
            this.getlist(0,0);
            this.getlist(0,5);
            Bus.$emit('addMessgeImg',{code:0,info:"分配成功"});
          }, f => {
            Bus.$emit('addMessgeImg',{code:3,info:f.msg});
          })
        },

      //查询结果按钮
      distributionOutcomesList(item){
        this.$router.push({
          name: "distributionOutcomesList",
          params: {item:item}
        })
      },

      //平均分配按钮
      average(item){
          this.nowlist=item;
          this.showmessages=true;
      },

      //切换状态界面按钮
      changecount(index){
        this.count=index;
        var t="";
        t=index-1;
        if(index==2){
            t=3
        }
        if(index==3){
          t=1
        }
        if(index==4){
          this.list=[];
          this.getlist(0, 2);
          this.getlist(0, 6);
          return;
        }
        if(index==1){
          this.list=[];
          this.getlist(0, 0);
          this.getlist(0, 5);
          return;
        }
        else {
          this.getlist(1, t);
        }
      },

      //页面初始请求
      getlist(count,status){
       var url="/areaPricersh/listMarketAreaPriceRsh";
       let data={};
       data.pageNo=1;
       data.respPersonCode=JSON.parse(localStorage.getItem('infoess')).personCode;
       data.status=status;
          this.$api.post(url, data, r => {
              if(count==1) {
                this.list=[];
                for(var i=0;i<r.data.length;i++){
                  this.list.push(r.data[i])
                }
              }
              else{
                  for(var i=0;i<r.data.length;i++){
                      this.list.push(r.data[i])
                  }
                  console.log( this.list.length)
              }
          }, f => {
            Bus.$emit('addMessgeImg',{code:3,info:f.msg});
          })
      },
    },
    created(){
      this.list=[];
        this.getlist(0,0);
        this.getlist(0,5);
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
    padding-top: 4rem;
  }
  .hasnovalue{
    width: 7.5rem;
    height: 9rem;
  }
  .flexbox{
    display: flex;
  }
  .count-bigbox{
    margin-top: 0.8rem;
  }
  .backbtn{
    width: 2.54rem;
    height:0.88rem;
    text-align: center;
    line-height: 0.88rem;
    color: white;
    background: #3F86FF;
    font-size: 0.34rem;
    border-radius: 0.1rem;
    margin: 0 auto;
    margin-top: 1.32rem;
  }
  .xiaozi{
    font-size: 0.3rem;
    color: #9EA5B6;
    margin-top: 0.28rem;
    text-align: center;
  }
  .boxzi{
    font-size: 0.52rem;
    color: #394D61;
    margin-top: 0.37rem;
    text-align: center;
    font-weight: bold;
  }
  .mes{
    width: 2.52rem;
    height: 2.03rem;
    background: url("../../assets/jpgs/fenpei.png") no-repeat;
    background-size: 100% 100%;
    margin:0 auto;
    margin-top: 1.66rem;
  }
  .sucssuce{
    width: 7.5rem;
    height:100vh;
    background: white;
    position: fixed;
    top: 0;
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
    font-weight: bold;
    font-size: 0.3rem;
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
    width: 7.5rem;
    height:100vh;
    position: fixed;
    top: 0;
    background: rgba(0,0,0,0.5);
  }
  .number{
    height:0.24rem;
    background:rgba(255,65,60,1);
    border-radius:50%;
    display: block;
    padding-left:0.05rem;
    padding-right: 0.05rem;
    position: absolute;
    top: 0.2rem;
    right: 0.03rem;
    line-height: 0.24rem;
    color: white;
    font-size: 0.2rem;
  }
  .s0{
    color: #2479E5;
  }
  .s1{
    color: #FF5A00;
  }
  .s2{
    color: #0AC26A;
  }
  .s3{
    color: #FFA200;
  }
  .s4{
    color: #F43622;
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
    text-align: center;
    line-height: 0.7rem;
  }
  .status{
    font-size: 0.3rem;
    float: right;
  }
  .lines{
    width: 100%;
    overflow: hidden;
    margin-top: 0.3rem;
  }
  .fenbtn{
    width:1.64rem;
    height:0.68rem;
    background:rgba(63,134,255,1);
    border-radius:0.06rem;
    color: white;
    font-size:.28rem;
    text-align: center;
    float:right;
    line-height: 0.72rem;
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
  .titles{
    color: #303030;
    font-size: 0.32rem;
    font-weight: bold;
    float:left;
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
  .bigbox{
    overflow:hidden;
    background: white;
    position: fixed;
    top: 0;
    width: 7.5rem;
  }
  .bigbox .flexs{
    text-align: center;
    line-height:0.84rem;
    position: relative;
    font-size: 0.28rem;
  }
  .bigbox .flexs p{
    position: absolute;
    bottom: 0;
    width:0.44rem;
    height: 0.08rem;
    background:#3F86FF;
    margin-left:50%;
   transform: translateX(-50%);
  }
  .nocolor{
    color: #9A9A9A;
  }
  .color{
    color:#4E4E4E;
    font-weight: bold;
  }
</style>
