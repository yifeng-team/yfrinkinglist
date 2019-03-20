/**
 * Created by Administrator on 2018/4/19.
 */
import md5 from 'js-md5';

export default class checksign {
  constructor(token, echostring) {
    this.token = token ? token : Math.random().toString(36).substring(2, 15)
    this.echostring = echostring ? echostring : Math.random().toString(36).substring(2, 15)
    this.timestamp = Math.random().toString(36).substring(2, 15)
    this.nonce =  Math.random().toString(36).substring(2, 15)
    this.signature = md5(this.timestamp +'#' + this.nonce + '#' + this.token)
  }

  toString () {
    return 'token=' + this.token + '&echostring=' + this.echostring + '&timestamp=' + this.timestamp + '&nonce=' + this.nonce + '&signature=' + this.signature;
  }

  static apendSign(url) {
    return url  + '?' + new checksign().toString();
  }
}
