package com.yfdyf.recommend.qywechat.api;

import com.alibaba.fastjson.JSONObject;
import com.yfdyf.recommend.qywechat.model.BaseMessage;
import com.yfdyf.recommend.qywechat.util.WeiXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMessageService {

    private static Logger logger = LoggerFactory.getLogger(SendMessageService.class);

    private  static  String sendMessage_url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    /**
     * @desc ：0.公共方法：发送消息
     * @param accessToken
     * @param message void
     */
    public JSONObject sendMessage(String accessToken,BaseMessage message){

        //1.获取json字符串：将message对象转换为json字符串
        String jsonMessage = JSONObject.toJSONString(message);
        //2.获取请求的url
        String url=sendMessage_url.replace("ACCESS_TOKEN", accessToken);
        //3.调用接口，发送消息
        JSONObject jsonObject = WeiXinUtil.httpRequest(url, "POST", jsonMessage);
        //4.错误消息处理
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                logger.error("------------信息发送失败------------ errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }
        logger.info("------------信息发送成功------------");
        return jsonObject;
    }
}
