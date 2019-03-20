package com.yfdyf.recommend.qywechat.util;

public class WxRequestUrl {

    public static final String WX_USER_DETAIL_API = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail";
    public static final String WX_USER_INFO_API = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
    public static final String WX_GET_TOKEN_API = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    public static final String WX_GET_JSAPI_TICKET_API = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=";


    //获取临时素材(视频不能使用https协议)
    public static final String WX_TMP_MATERIAL_API = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    //获取临时素材(视频)
    public static final String WX_TMP_MATERIAL_VIDEO_API = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    public static final String VIDEO = "video";

    /**
     *  构造获取AccessToken的URL
     * @param corpid
     * @param corpsecret
     * @param agentid
     * @return
     */
    public static  String getAccessTokenUrl(String corpid, String corpsecret, String agentid){
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(WX_GET_TOKEN_API);
        sbUrl.append("?corpid=");
        sbUrl.append(corpid);
        sbUrl.append("&corpsecret=");
        sbUrl.append(corpsecret);
        sbUrl.append("&agentid=");
        sbUrl.append(agentid);
        return sbUrl.toString();
    }

    public static  String getJsapiTicketUrl(String accessToken){
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(WX_GET_JSAPI_TICKET_API);
        sbUrl.append(accessToken);
        return sbUrl.toString();
    }


    public static  String getUserInfoUrl(String accessToken, String wxCode){
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(WX_USER_INFO_API);
        sbUrl.append("?access_token=");
        sbUrl.append(accessToken);
        sbUrl.append("&code=");
        sbUrl.append(wxCode);
        return sbUrl.toString();
    }

    public static  String getUserDetailUrl(String accessToken){
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(WX_USER_DETAIL_API);
        sbUrl.append("?access_token=");
        sbUrl.append(accessToken);
        return sbUrl.toString();
    }


    public static  String getGetTmpMaterialUrl(String accessToken, String mediaId){
        return String.format(WX_TMP_MATERIAL_API, accessToken, mediaId);
    }

    public static  String getGetTmpMaterialVideoUrl(String accessToken, String mediaId){
        return String.format(WX_TMP_MATERIAL_VIDEO_API, accessToken, mediaId);
    }

    public static String getMediaUrlByType(String type,String token, String mediaId){
        if(VIDEO.equalsIgnoreCase(type)){
            return getGetTmpMaterialVideoUrl(token, mediaId);
        }else{
            return getGetTmpMaterialUrl(token, mediaId);
        }
    }


}
