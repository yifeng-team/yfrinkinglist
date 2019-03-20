import Vue from 'vue'
import Router from 'vue-router'
import ChoiceIndex from '@/components/ChoiceIndex'
import distribution from '@/components/ShopManager/distribution'
import distributionOutcomesList from '@/components/ShopManager/distributionOutcomesList'
import distributionOutcomes from '@/components/ShopManager/distributionOutcomes'
import lookAllImages from '@/components/ShopManager/lookAllImages'
import Marklist from '@/components/ShopManager/Marklist'
import examineAndApprove from '@/components/ShopManager/examineAndApprove'
import maintains from '@/components/GeneralStaff/maintains'
import Pending from '@/components/GeneralStaff/Pending'
import viewTtheImage from '@/components/GeneralStaff/viewTtheImage'
import productList from '@/components/GeneralStaff/productList'

// import ShowMap from '@/components/ShowMap'
Vue.use(Router)

export default new Router({
  // mode: 'history',
  /*base: 'siting',*/
  routes: [

    {
      path: '/',
      name: 'ChoiceIndex',
      component: ChoiceIndex,
      meta:{
        title:'市场调研'
      },
    },
    {
      path: '/distribution',
      name: 'distribution',
      component: distribution,
      meta:{
        title:'待分配'
      },
    },
    {
      path: '/distributionOutcomesList',
      name: 'distributionOutcomesList',
      component: distributionOutcomesList,
      meta:{
        title:'分配结果'
      },
    },
    {
      path: '/distributionOutcomes',
      name: 'distributionOutcomes',
      component: distributionOutcomes,
      meta:{
        title:'分配结果'
      },
    },
    {
      path: '/Marklist',
      name: 'Marklist',
      component: Marklist,
      meta:{
        title:'市调单'
      },
    },
    {
      path: '/maintains',
      name: 'maintains',
      component: maintains,
      meta:{
        title:'分配结果'
      },
    },
    {
      path: '/Pending',
      name: 'Pending',
      component: Pending,
      meta:{
        title:'待处理'
      },
    },
    {
      path: '/productList',
      name: 'productList',
      component: productList,
      meta:{
        title:'分配结果'
      },
    },
    {
      path: '/viewTtheImage',
      name: 'viewTtheImage',
      component: viewTtheImage,
      meta:{
        title:'商品图片'
      },
    },
    {
      path: '/examineAndApprove',
      name: 'examineAndApprove',
      component: examineAndApprove,
      meta:{
        title:'商品审批'
      },
    },
    {
      path: '/lookAllImages',
      name: 'lookAllImages',
      component: lookAllImages,
      meta:{
        title:'商品图片'
      },
    },
  ]
})
