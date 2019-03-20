<template>
  <div style="overflow: hidden">
    <div class="header"  v-show="!beginTo&&(this.$route.params.item.status=='1'||this.$route.params.item.status=='4')"  v-if="lookimg.length<10">
      <span>共上传{{lookimg.length}}张图片</span>
      <div class="up"  @click="chooseImage">上传</div>
    </div>
    <!--暂无图片 -->
    <div class="notupian" v-show="lookimg.length==0">
      <img src="../../assets/jpgs/chahua@2x.png"/>
      <p>暂无图片~~</p>
    </div>

    <div>
      <ul class="myphoto" v-show="!beginTo">
        <li v-for="item,i in lookimg">
          <img class="photo" @click="changephoto(i,item)" v-bind:src="item" :class="{isshow:!item.ismouseshowphto}"/>
          <span class="deletephtos" @click="item.ismouseshowphto=!item.ismouseshowphto" :class="{isshow:item.ismouseshowphto}"></span>
          <div @click="deletephtos(item)" v-if="deletes"></div>
        </li>
      </ul>
      <!--照片轮播-->
      <div @click="closePh" v-show="beginTo&&!pingguo" style="background:rgba(0,0,0,0.5)">
        <wc-swiper v-if="showPhon.length>0">
          <wc-slide v-for="item,index in showPhon" :key="index"  :style="deletes?'height:100vh;position: relative;':'height:100vh;position: relative;'">
            <img class="myphotothis"  :style="'margin-top:-'+stylelist[index]+'px'" :src="item"/>
            <!--<div class="myphotothis" :style="{background:'url('+slide.localIds+') no-repeat'}"></div>-->
          </wc-slide>
        </wc-swiper>
      </div>

      <!--照片轮播-->
      <div @click="closePh" v-show="beginTo&&pingguo" style="background:rgba(0,0,0,0.5)">
        <wc-swiper v-if="showPhon.length>0">
          <wc-slide v-for="item,index in showPhon" :key="index"  style="height:100vh;position: relative;">
            <img class="nophoto"   :src="item"/>
            <!--<div class="myphotothis" :style="{background:'url('+slide.localIds+') no-repeat'}"></div>-->
          </wc-slide>
        </wc-swiper>
      </div>


    </div>

    <!--弹框（确认提交）-->
    <div class="messges" v-show="showsubmit">
      <div class="mybox">
        <p class="meggestitle">是否确认删除该相片？</p>
        <div class="messgesbtn">
          <div class="no" @click="nothis()">取消</div>
          <div class="yes" @click="yesthis()">确定</div>
        </div>
      </div>
    </div>
    <div class="fanhui" @click="fanhui()">确定</div>
  </div>
</template>
<script>
  import 'wc-swiper/style.css'
  import wx from 'weixin-js-sdk'
  import Bus from '../bus.js'
  //import { MessageBox } from "mint-ui";
  //  new Swiper("#buyCon", {
  //    observer:true,
  //    observeParents:true
  //  });

  export default {
    name:"viewTtheImage",
    data() {
      return {
        nowitem:"",
        showsubmit:false,
        showPhon:[],
        showPhons:[],
        pingguo:"",//是否为苹果手机
        beginTo:false,
        beginTos:false,
        stylelist:[],
        imgUrl:[],
        lookimg:[],
        mediaIds:"",
        deletes:false,
        selectSiteAttachList: [
          {
            attachId: "",
            createCode: "",
            createTime: "",
            creatorName: "",
            fileName: "",
            mediaId: "",
            siteApplyNo: ""
          }
        ],
        attachLists:[{localIds:"http://h.hiphotos.baidu.com/zhidao/pic/item/c8ea15ce36d3d53959f753fd3887e950342ab0c2.jpg"},
          {localIds:"http://imgsa.baidu.com/exp/w=500/sign=5eadd660878ba61edfeec82f713597cc/ac6eddc451da81cbd64353675766d016082431f7.jpg"},
          {localIds:"http://img1.imgtn.bdimg.com/it/u=347099376,1308399272&fm=214&gp=0.jpg"},
        ]
      };
    },
    components: {Bus},
    methods: {
      fanhui(){
        this.$router.go(-1);
      },
      nothis(){
        this.showsubmit=false;
      },
      yesthis(){
        this.$api.get("/empPricersh/deletePicture?"+this.nowitem.substring(this.nowitem.indexOf("value"),this.nowitem.length), {}, r => {
          Bus.$emit('addMessgeImg',{code:0,info:"删除成功"});
          this.showsubmit=false;
          this.imgUrl=[];
          this.getlist();
        }, f => {
          Bus.$emit('addMessgeImg',{code:1,info:f.msg});
        })
      },
      deletephtos(item){
          this.showsubmit=true;
         this.nowitem=item;
      },
      yesto(){
        let data={};
        data.empBillCode=this.$route.params.item.empBillCode;
        data.billCode=this.$route.params.item.billCode;
        data.planBillCode=this.$route.params.item.planBillCode;
        data.mediaIds=this.mediaIds.substring(0,this.mediaIds.length-1);
        this.$api.post("/empPricersh/uploadPicture", data, r => {
          Bus.$emit('addMessgeImg',{code:0,info:"上传成功"});
          this.imgUrl=[];
          this.getlist();
        }, f => {
          Bus.$emit('addMessgeImg',{code:3,info:f.msg});
        })
      },
      changephoto:function (i,item) {
          //item表示当前图片
        var list=this.lookimg;
       var nowlist=[];
       var nowi=null;
        for(var i=0;i<list.length;i++){
            if(item==list[i]){
              nowi=i;//将当前图片的位置保存
            }
            if(nowi!=null){
              nowlist.push(list[i])
            }
        }
        for(var i=0;i<nowi;i++){
          nowlist.push(list[i])
        }

        this.showPhon=[]
        this.showPhon=nowlist;
        this.beginTo=true;
        this.changeheight();
      },
      changeheight(){
//        this.pingguo=true;
       if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
          this.pingguo=true;
        }else{
          this.pingguo=false;
          var thiss=this;
          this.$nextTick(function(){
            thiss.stylelist=[];
            var heights=document.getElementsByClassName("myphotothis");
            for(var s=0;s<heights.length;s++){
              thiss.stylelist.push((heights[s].offsetHeight)/2);
            }
          })
        }
      },
      closePh:function(){
        this.showPhon=[];
        this.beginTo=false
      },
      checkSign:function(){
        var currentUrl = encodeURIComponent(location.href.split('#')[0]);
        this.$api.get("/wechat/checkSign?url="+currentUrl, {}, r => {
          var result = r.data;
          wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: result.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
            timestamp: result.timestamp, // 必填，生成签名的时间戳
            nonceStr: result.nonceStr, // 必填，生成签名的随机串
            signature:result.signature,// 必填，签名，见附录1
            jsApiList: ['chooseImage','previewImage','getLocalImgData','uploadImage','downloadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
          });
        }, f => {
          Bus.$emit('addMessgeImg',{code:1,info:'获取数据失败'});
        })
      },
      //点击选择照片按扭
      chooseImage:function() {
        this.mediaIds="";
        this.imgUrl=[];
        var _this=this;
        wx.chooseImage({
          count: 10-this.lookimg.length, // 默认9
          sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
          sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
          success: function (res) {
            _this.syncUpload(res.localIds);
            _this.showphoto=false;
          }
        });
      },
      syncUpload:function(localIds){
        var _this=this;
        if(localIds.length>0){
          var localId = localIds.pop();
          wx.uploadImage({
            localId: localId.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
            isShowProgressTips: 1, // 默认为1，显示进度提示
            success: function (res) {
              _this.uploadMedia(res.serverId,localIds.length);
              window.setTimeout(function(){
                _this.syncUpload(localIds);
              },1000);
            }
          });
        }
      },
      uploadMedia:function(mediaId,length){
        let data={mediaId:mediaId};
        this.$api.get("/wechat/uploadMeiaId", data, r => {
          var imgUrl1 =this.$api.imagePath+mediaId+".jpg";
          this.imgUrl.push({"localIds":imgUrl1,"ismouseshowphto":true})
          this.selectSiteAttachList.push({"mediaId":mediaId,});
          this.mediaIds+=mediaId+",";
          this.stylelist=[];
          var heights=document.getElementsByClassName("myphotothis");
          for(var s=0;s<heights.length;s++){
            this.stylelist.push((heights[s].offsetHeight)/2);
          }

          if(length==0){
              this.yesto();
          }
        }, f => {
        })
      },

      getlist(){
          this.lookimg=[];
        let data={};
        data.empBillCode=this.$route.params.item.empBillCode;
        this.$api.get("/empPricersh/getPictureValue?empBillCode="+ data.empBillCode, {}, r => {
          for(var i=0;i<r.data.rows.length;i++) {
            this.lookimg.push(this.$api.imagePath+"/empPricersh/downloadPicture?value="+r.data.rows[i])
          }
        }, f => {
          Bus.$emit('addMessgeImg',{code:1,info:f.msg});
        })
      },
    },
    created(){
      this.checkSign()
      this.getlist();
      this.deletes=this.$route.params.item.deletes;
      console.log(this.$route.params.item.status)
    },
  };
</script>

<style scoped>
  .fanhui{
    width: 100%;
    height: 0.88rem;
    background: #3F86FF;
    color: white;
    font-size: 0.32rem;
    text-align: center;
    line-height: 0.88rem;
    position: fixed;
    bottom: 0;
  }
  .notupian p{
    font-size: 0.28rem;
    color: #9a9a9a;
    text-align: center;
    margin-top: 0.5rem;
  }
  .notupian img{
    width: 3.44rem;
    height: 2.38rem;
    margin: 0 auto;
    display: block;
    padding-top: 3.2rem;
  }
  .notupian{
    width: 7.5rem;
    overflow: hidden;
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
  ul li{
    list-style: none;
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
  :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #B5B5B5; opacity:1;
  }

  ::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #B5B5B5;opacity:1;
  }
  input:-ms-input-placeholder{
    color: #B5B5B5;opacity:1;
  }

  input::-webkit-input-placeholder{
    color: #B5B5B5 ;opacity:1;
  }
  .myphotobox{
    width: 100%;
    padding-bottom:0.06rem;
    background: white;
    overflow: hidden;
  }
  .myphoto{
    width: 100%;
    padding-left: 0.25rem;
    overflow: hidden;
    margin-top: 0.2rem;
  }
  .myphoto li div{
    width:0.32rem;
    height: 0.32rem;
    background: url("../../assets/jpg/delete.png") no-repeat;
    background-size: 0.32rem 0.32rem;
    position: absolute;
    top: 0rem;
    right: -0.05rem;
  }

  .myphoto li{
    width: 1.65rem;
    height: 1.8rem;
    padding-top: 0.15rem;
    float:left;
    margin-bottom: 0.2rem;
    margin-right:0.15rem;
    position: relative;
  }
  .myphoto li .photo{
    width: 1.64rem;
    height: 1.64rem;
  }
  .myphoto li .photos{
    position:fixed;
    width: 100%;
    height: 100vh;
    top: 0;
    left: 0;
    z-index: 9999;
  }
.nophoto{
  margin-left: 50%;
  transform: translateX(-50%);
  max-width: 6.9rem;
  max-height: 80vh;
  min-width: 5rem;
  margin-top: 1rem;
}
  .myphotothis{
    margin-left: 50%;
    transform: translateX(-50%);
    /*vertical-align: middle;*/
    position: absolute;
    top: 50%;
    max-width: 6.9rem;
    max-height: 80vh;
    min-width: 5rem;
  }
  .zuohui{
    background: url("../../assets/jpgs/zuohui.png") no-repeat;
    background-size: 100% 100%;
  }
  .zuolan{
    background: url("../../assets/jpgs/zuo.png") no-repeat;
    background-size: 100% 100%;
  }
  .youhui{
    background: url("../../assets/jpgs/youhui.png") no-repeat;
    background-size: 100% 100%;
  }
  .youlan{
    background: url("../../assets/jpgs/youlan.png") no-repeat;
    background-size: 100% 100%;
  }


  .btn p{
    text-align: center;
    color: #9A9A9A;
    font-size: 0.32rem;
    line-height: 0.76rem;
  }
  .btn>div{
    width: 0.76rem;
    height: 0.76rem;
    border-radius: 50%;
    top: 0;
  }
  .left{
    position: absolute;
    left: 0;
  }
  .right{
    position: absolute;
    right: 0;
  }
  .btn{
    width: 3.56rem;
    height: 0.76rem;
    margin: 0 auto;
    position: absolute;
    bottom: 1rem;
    margin-left: 50%;
    transform: translateX(-50%);
  }
  .imgs img{
    max-width: 6.9rem;
  }
  .imgs{
    overflow: hidden;
    position: absolute;
    margin: 0 auto;
    margin-top: 50%;
    transform: translateY(-50%);
    background: red;
  }
  .up{
    width: 1.34rem;
    height: 0.56rem;
    background: #3F86FF url("../../assets/jpgs/upthis.png") no-repeat;
    background-size: 0.22rem 0.26rem;
    background-position: 0.23rem 0.14rem;
    float: right;
    line-height: 0.56rem;
    text-align: center;
    color: white;
    padding-left:0.34rem;
    box-sizing: border-box;
    font-size: 0.28rem;
    border-radius: 0.04rem;
    margin-top: 0.15rem;
  }
  .header{
    width: 100%;
    height: 0.9rem;
    background: white;
    font-size: 0.28rem;
    color: #727272;
    line-height: 0.9rem;
    padding-left: 0.24rem;
    padding-right: 0.24rem;
    box-sizing: border-box;
  }

</style>
