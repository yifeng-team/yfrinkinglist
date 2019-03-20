/**
 * Created by Administrator on 2018/4/18.
 */
import api from './index'
import checksign from './checksign.js'

export default {
  getQueryString: (name) => {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
      return unescape(r[2]);
    }
    return null;
  },
   GetHashQueryString: (name) => {
    var after = window.location.hash.split("?")[1];
     if(after)
     {
       var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
       var r = after.match(reg);
       if(r != null)
       {
         return  decodeURIComponent(r[2]);
       }
       else
       {
         return null;
       }
     }
  },
  getWxUrl: function () {//初始化微信JS配置
    var urlVal = window.location.href;
    if(0 < urlVal.indexOf("#")) {
      urlVal = urlVal.substr(0, urlVal.indexOf("#"));
    }
    console.log(urlVal);
    return urlVal;
  },
  queryJsConfig: (param) => {
    return new Promise((resolve, reject) => {
      api.post(checksign.apendSign('/person/jsconfig'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  getWxUserByCode:(param) =>{
    return new Promise((resolve, reject) => {
      api.get(checksign.apendSign('/person/userInfo/wxCode'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  getUserInfo: (param) => {
    return new Promise((resolve, reject) => {
      api.get(checksign.apendSign('/wechat/getuserinfo'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  getAccessToken: (param) => {
    return new Promise((resolve, reject) => {
      api.get(checksign.apendSign('/wechat/accesstoken'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  getUserDetail: (param) => {
    return new Promise((resolve, reject) => {
      api.post(checksign.apendSign('/wechat/getuserdetail'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  getBasePersonInfo:(param) => {
    return new Promise((resolve, reject) => {
      api.get(checksign.apendSign('/person/showInfo'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  getJsApiTicket: (param) => {
    return new Promise((resolve, reject) => {
      api.get(checksign.apendSign('/wechat/JsapiTicket'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  getPersonInfo: (param) => {
    return new Promise((resolve, reject) => {
      api.get(checksign.apendSign('/wechat/userinfo'), param).then(r => {
        resolve(r)
      }).catch(e => {
        reject(e)
      });
    });
  },
  prefixInteger: (num, length) =>{
    return (Array(length).join('0') + num).slice(-length);
  }
}
