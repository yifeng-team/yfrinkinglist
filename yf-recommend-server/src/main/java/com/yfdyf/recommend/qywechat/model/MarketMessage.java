package com.yfdyf.recommend.qywechat.model;

import lombok.Data;

/**
 * 市调单推送的相关信息
 * Created by YF on 2019/1/20.
 */
@Data
public class MarketMessage {
    private String personCode;
    private String personName;
    private String storeCode;
    private String storeName;
    private String billCode;
    private String billName;
    private String billType;
    private String endTime;

    /**
     * 信息标题
     */
    private String title;
}
