<template>
    <div>
        <img src="../../assets/jpgs/goback.png" style="width:1.04rem;height:1.04rem;" alt="" class="shouye" id="moveDiv"  @touchstart.stop="down"  @touchmove.prevent="move" @touchend.stop="end" @click="returnhome">
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
                moveDiv.style.left = this.xPum + "px";

                if (this.yPum < 0) {
                    this.yPum = 0;
                } else if (this.yPum > this.fullheight) {
                    this.yPum = this.fullheight;
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
            }
        },
        //鼠标释放时候的函数
        end() {
            this.flags = false;
        },
        returnhome() {
          this.$router.push("ChoiceIndex");
        }
    }
};
</script>

<style>
.shouye {
    position: fixed;
  bottom: 1rem;
  left:0.5rem;
    width: 1.653333rem;
    height: 1.653333rem;
}
</style>
