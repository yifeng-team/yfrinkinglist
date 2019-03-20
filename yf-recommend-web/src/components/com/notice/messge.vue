<template>
  <div v-show="showList.length>0">
    <ul>
      <li v-for="item in showList" :class="messgebg[item.status]">
        {{item.info}}
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: 'messge',
  data () {
    return {
        messgebg:['messge-success','messge-warning', 'messge-errr']
    }
  },
  props: ['showList'],
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
  @import "../../../../static/css/com/notice/messge.css" ;
  .messge-success{
    background: green;
    color: #FFF;
  }
  .messge-warning{
    background: yellow;
    color: red;
  }
  .messge-errr{
    background: red;
    color: #FFF;
  }
</style>
