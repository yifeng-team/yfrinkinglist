<template>
    <div class="box">
      <img src="../assets/jpgs/goback.png" style="width:1.04rem;height:1.04rem;" alt="" class="shouye" id="moveDiv" @mousedown.stop.prevent="down" @touchstart="down" @mousemove.prevent="move" @touchmove.prevent="move" @mouseup.stop.prevent="end" @touchend.stop.prevent="end" >
    </div>
</template>
<script>
//import { MessageBox } from "mint-ui";
export default {
  name:"goback",
    data() {
        return {
            flags: false,
            position: {
                x: 0,
                y: 0
            },
            nx: "",
            ny: "",
            dx: "",
            dy: "",
            isclick:true,//是否点击
            lastnx:"",
            xPum: "",
            yPum: "",
            fullheight: "",
            fullwidth: ""
        };
    },
    components: {},
    methods: {
        // 实现移动端拖拽
        down() {
            this.flags = true;
            var touch;
            if (event.touches) {
                touch = event.touches[0];
            } else {
                touch = event;
            }
            this.position.x = touch.clientX;
            this.position.y = touch.clientY;
            this.dx = moveDiv.offsetLeft;
            this.dy = moveDiv.offsetTop;
            this.isclick=false;
        },
        move() {
            if (this.flags) {
                var touch;
                if (event.touches) {
                    touch = event.touches[0];
                } else {
                    touch = event;
                }
                this.nx = touch.clientX - this.position.x;
                this.ny = touch.clientY - this.position.y;
                this.lastnx=this.nx;//保存上一次的nx
                this.xPum = this.dx + this.nx;
                this.yPum = this.dy + this.ny;
                //获取整个屏幕的宽高
                this.fullheight = document.documentElement.clientHeight;
                this.fullwidth = document.documentElement.clientWidth;
                //判断上下移动的距离
                if (this.xPum < 0) {
                    this.xPum = 0;
                } else if (this.xPum > this.fullwidth) {
                    this.xPum = this.fullwidth;
                }
                moveDiv.style.left = this.xPum+130 + "px";

                if (this.yPum < 0) {
                    this.yPum = 0;
                } else if (this.yPum > this.fullheight) {
                    this.yPum = this.fullheight;
                }
                if(this.yPum<350){
                    this.yPum=350;
                }

                moveDiv.style.top = this.yPum + "px";

                //阻止页面的滑动默认事件
                document.addEventListener(
                    "touchmove",
                    function() {
                        event.preventDefault();
                    },
                    false
                );
              this.isclick=true;
            }
        },
        //鼠标释放时候的函数
        end() {
          if(this.isclick==false) {
            if (this.lastnx - this.nx == 0 || this.nx == "" && this.ny == "") {
              this.returnhome();
            }
          }
            this.flags = false;
        },
        returnhome() {
          this.$router.push({
            name: "ChoiceIndex",
            params: {}
          })
        }
    }
};
</script>
<style scoped>
.shouye {
    position: fixed;
    bottom: 1.5rem;
    width: 1.653333rem;
    height: 1.653333rem;
    z-index: 10;
    left:50%;
    transform: translateX(-50%);
    margin-left:-130px;
}
</style>
