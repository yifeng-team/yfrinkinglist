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
    name: 'iconcellno',
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
    background: white;
  }
  .cell{
    position: relative;
    font-size: 0.30rem;
    line-height: 0.9rem;
    color: #303030;
    padding:0 0.25rem 0 0.25rem;
    overflow: hidden;
    border-bottom: 0.01rem solid #F1F3F4;
    background: white;
  }
  .cellright{
    float: right;
    /*margin-right: 0.2rem;*/
  }
  .cellright span{
    width: 3.3rem;
    height: 0.4rem;
    display: block;
    text-align: right;
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
  }
  .quan{
    width: 100%;
    height: 100%;
  }
</style>
