<template>
  <div>
    <span>10月厂家维护品种市调</span>
    <span>截止至2018-10-31</span>
    <span>QIKFSAJKDYWU15421</span>
    <span>老百姓、养天和</span>
    <span>常规市调</span>
    <span>共38个商品 已经分配：{{complete}}个</span>
    <button @click="average()" v-show="showcomplete">平均分配</button>
    <button @click="distributionOutcomesList()" v-show="!showcomplete">查看分配结果</button>
    <button v-show="!showcomplete">提交</button>
  </div>
</template>
<script>
  import Bus from '../bus.js'
  import popup from '../com/notice/popup'
  //import { MessageBox } from "mint-ui";
  export default {
    name:"distribution",
    data() {
      return {
        complete:0,
        showcomplete:true,
      };
    },
    components:{
      popup,
    },
    methods: {
      distributionOutcomesList(){
        this.$router.push({
          name: "distributionOutcomesList",
          params: {}
        })
      },
      average(){
          this.complete=38;
          this.showcomplete=false;
      }
    }
  };
</script>

<style scoped>

</style>
