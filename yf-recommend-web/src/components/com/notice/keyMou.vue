<template>
  <div class="index">
    <div class="key-head">
        <ul>
          <li @click="keyClick(0)">
            <slot name="cancel" >
              <span>完成</span>
            </slot>
          </li>
          <li  @click="keyClick(2)">
            <div class="key-head-icon"></div>
          </li>
          <li style="color: #0ac26a" @click="keyClick(1)">
            <slot name="next" >
              <span>下一个</span>
            </slot>
          </li>
        </ul>
    </div>
    <div class="key-up" v-show="showKeys">
      <ul>
        <li v-for="item in  keys" @click="setNumber(item.value)" :style="item.style"></li>
      </ul>
    </div>
  </div>
</template>
<script>
  import Bus from '../../bus.js'
export default {
  name: 'keyMou',
  data () {
    return {
      keys:[{value:'1',style:{ backgroundPositionX: '0rem', backgroundPositionY: '0rem'}},
        {value:'2',style:{ backgroundPositionX: '-2.5rem', backgroundPositionY: '0rem'}},
        {value:'3',style:{ backgroundPositionX: '-5rem', backgroundPositionY: '0rem'}},
        {value:'4',style:{ backgroundPositionX: '0rem', backgroundPositionY: '-1rem'}},
        {value:'5',style:{ backgroundPositionX: '-2.5rem', backgroundPositionY: '-1rem'}},
        {value:'6',style:{ backgroundPositionX: '-5rem', backgroundPositionY: '-1rem'}},
        {value:'7',style:{ backgroundPositionX: '0rem', backgroundPositionY: '-1.98rem'}},
        {value:'8',style:{ backgroundPositionX: '-2.5rem', backgroundPositionY: '-1.98rem'}},
        {value:'9',style:{ backgroundPositionX: '-5rem', backgroundPositionY: '-1.98rem'}},
        {value:'.',style:{ backgroundPositionX: '-0rem', backgroundPositionY: '-2.98rem'}},
        {value:'0',style:{ backgroundPositionX: '-2.5rem', backgroundPositionY: '-2.98rem'}},
        {value:'d',style:{ backgroundPositionX: '-5rem', backgroundPositionY: '-2.98rem'}}
      ],
    }
  },
  props: ['showKeys'],
  components: {},
  created:function(){
  },
  computed:{
  },
  methods:{
    setNumber:function(number){
        this.$emit('callBack',{name:0,value:number})
    },
    keyClick:function(number){//0是完成 1是下一个
      this.$emit('callBack',{name:1,value:number})
    }
  }
}
</script>
<style scoped>
  .index{
    z-index: 20;
    position: fixed;
    bottom: 0;
    background: white;
  }
  .key-up{
    margin: auto;
    width: 7.5rem;
  }
  .key-up>ul>li{
    float: left;
    width: 2.49rem;
    height: 1rem;
    margin-right: 0.01rem;
    margin-bottom: 0.01rem;
    list-style-type: none;
    background:rgba(0,0,0,0) url("../../../assets/jianpan.png") no-repeat;
    background-size: 7.5rem;
  }
  .key-head{
    background: white;
    overflow: hidden;
  }
  .key-head>ul>li{
    float: left;
    width: 2.5rem;
    height: 1rem;
    line-height: 1rem;
    font-size: 0.3rem;
    color: #303030;
    text-align: center;
    list-style-type: none;
    background: #F2F2F3;

  }
  .key-head>ul>li>.key-head-icon{
    height: 100%;
    width: 1rem;
    margin: auto;
    background: url("../../../assets/jpgs/icon.png") no-repeat;
    background-size: 7.5rem;
    background-position-x: -0.15rem;
    background-position-y: -8.88rem;
  }
</style>
