# yf-recommend-parent

益丰绩效看板目

# 益丰移动

#### 前台项目说明
1. yf-recommend-web项目为前端项目,开发时候依赖node的运行环境
 nodejs环境配置好以后，在yf-recommend-web目录下面 
   A. 执行 npm install
   B. 执行 npm run dev
  
  配置文件：
  yf-recommend-web\config\index.js 修改对应的IP为本地IP
  注意此文件不需要提交 只是修改本地IP
  
  #### package.json 中修改IP 为自己本地IP 不要使用127.0.0.1
   "dev": "webpack-dev-server --inline --progress --config script/webpack.dev.conf.js --host 本机IP",

  ###接口调用配置
   yf-recommend-web\src\api\index.js中需要进行接口调用转发 前台的接口通过此地址发送到后台接口
   const  root = '接口地址'
   const  rootContext = '接口地址
   #发布到测试环境需要修改此应用
   
  
  
#####后端项目说明yf-recommend-server
 配置文件使用
 本地开发 启用 dev
 需要增加配置 3个文件都需要增加
 发布到测试环境 需要注释dev 放开test注释
 发布到生产 注释其它 放开prod
 spring:
   profiles:
     active: dev
 #     active: test
 #    active: prod

