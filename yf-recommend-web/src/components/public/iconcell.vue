<template>
  <div class="cell">
    <slot name="charu" class="quan">
       <span class="cellleft">
          <slot name="left">
            <span>右边内容</span>
          </slot>
      </span>
      <span class="cellright" style="float: right">
          <slot name="right">
            <span style="overflow-x: hidden">左边内容</span>
          </slot>
      </span>
      <span class="cellicon">
      </span>
    </slot>

  </div>
</template>
<script>
export default {
  //左右一边一个 带图标
  name: 'iconcell',
  data () {
    return {
    }
  },
  props: [],
  components: {
  },
  created:function(){

  },
  computed:{
  },
  methods:{
  }
}
</script>
<style scoped>
  .cell:active{
    background: #F8F8F8;
  }
  .cell{
    position: relative;
    font-size: 0.30rem;
    /*color: #303030;*/
    padding: 0rem 0.25rem 0rem 0.25rem;
    overflow: hidden;
    border-bottom: 0.01rem solid #F1F3F4;
    background: white;
    line-height: 0.9rem;
  }
  .cellright{
    float: right;
    margin-right: 0.2rem;
  }
  .cellright span{
    width: 4rem;
    height: 0.4rem;
    display: block;
    text-align: right;
    position: absolute;
    top: 0.25rem;
    right: 0.46rem;
  }
  .cellleft{
    float: left;
  }
  .cellicon{
    display: block;
    position: absolute;
    right: 0;
    top:0;
    margin-right: 0.2rem;
    width: 0.4rem;
    height: 0.76rem;
    background: url("../../../static/images/icon.png") no-repeat;
    background-size:7.5rem;
    background-position-x: -2.78rem;
    background-position-y: -2.94rem;
  }
  .quan{
    width: 100%;
    height: 100%;
  }
</style>
