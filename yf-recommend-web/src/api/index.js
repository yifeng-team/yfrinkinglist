/**
 * Created by Administrator on 2018/4/2.
 */
// 配置API接口地址
//测试环境
// const  root = 'http://192.168.1.212:7001'


//本地环境
// const  root = 'http://192.168.254.94:9088/market';
// const  imagePath = 'http://192.168.254.94:9088/market';
// const postroot='http://192.168.254.94:7001';



// 自己本机
// const  root = 'http://192.168.254.93:9088/market';
// const  imagePath = 'http://192.168.254.93:9088/market/images/';
// const postroot='http://192.168.254.93:7001';

//测试环境
const  root = 'http://192.168.7.73:9088/market';
const  imagePath = 'http://192.168.7.73:9088/market';
const postroot='http://192.168.7.73:7001';
//生产环境
// const  root = 'http://192.168.5.75:9082/recommend/api/v1'
// 引用axios
import axios from 'axios'

// 自定义判断元素类型JS
function toType(obj) {
  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase()
}

// 参数过滤函数
function filterNull(o) {
  for (var key in o) {
    if (o[key] === null) {
      delete o[key]
    }
    if (toType(o[key]) === 'string') {
      o[key] = o[key].trim()
    } else if (toType(o[key]) === 'object') {
      o[key] = filterNull(o[key])
    } else if (toType(o[key]) === 'array') {
      o[key] = filterNull(o[key])
    }
  }
  return o
}

/*
 接口处理函数
 这个函数每个项目都是不一样的，我现在调整的是适用于
 https://cnodejs.org/api/v1 的接口，如果是其他接口
 需要根据接口的参数进行调整。参考说明文档地址：
 https://cnodejs.org/topic/5378720ed6e2d16149fa16bd
 主要是，不同的接口的成功标识和失败提示是不一致的。
 另外，不同的项目的处理方法也是不一致的，这里出错就是简单的alert
 */

function apiAxios(method, url, params, success, failure) {
  document.getElementById("isshow").style.display = "block";
  if (params) {
    params = filterNull(params)
  }
  axios({
    method: method,
    url: url,
    data: method === 'POST' || method === 'PUT' ? params : null,
    params: method === 'GET' || method === 'DELETE' ? params : null,
    baseURL: root,
    withCredentials: false
  }).then(function (res) {
    document.getElementById("isshow").style.display = "none";
    if (res.data.success === true) {
      if (success) {
        success(res.data)
      }
    } else {
      if (failure) {
        failure(res.data)
      } else {
        window.alert('error: ' + JSON.stringify(res.data))
      }
    }
  }).catch(function (err) {
    let res = err.response
    if (err) {
      window.alert('api error, HTTP CODE: ' + res.status)
    }
  })
}

//增加一个方法 用于文件上传
function apiMutiAxios(url, formData,success, failure) {
  var options = {  // 设置axios的参数
    url: root+url,
    // url: mutliRoot+url,
    data: formData,
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  }
  document.getElementById("isshow").style.display = "block";

  axios(options).then(function (res) {
    document.getElementById("isshow").style.display = "none";
    if (res.data.success === true) {
      if (success) {
        success(res.data)
      }
    } else {
      if (failure) {
        failure(res.data)
      } else {
        window.alert('error: ' + JSON.stringify(res.data))
      }
    }
  }).catch(function (err) {
    let res = err.response
    if (err) {
      window.alert('api error, HTTP CODE: ' + res.status)
    }
  })

}


// 返回在vue模板中的调用接口
export default {
  get: function (url, params, success, failure) {
    return apiAxios('GET', url, params, success, failure)
  },
  post: function (url, params, success, failure) {
    return apiAxios('POST', url, params, success, failure)
  },
  postMutiForm: function (url, formData, success, failure) {
    return apiMutiAxios(url, formData, success, failure)
  },
  put: function (url, params, success, failure) {
    return apiAxios('PUT', url, params, success, failure)
  },
  delete: function (url, params, success, failure) {
    return apiAxios('DELETE', url, params, success, failure)
  },
  imagePath: imagePath,
  postroot: postroot,
}
