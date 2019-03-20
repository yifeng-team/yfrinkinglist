package com.yfdyf.recommend.qywechat.enums;

/**
 * 发送企业微信信息类型
 * Created by YF on 2019/1/20.
 */
public enum SendMessageType {
    //店长平均分配后，给店员推送信息
    BASE_AFTER_MANAGER_ALLOT,
    //定时任务，5天内还没提交
    TASK_OVER_TIME_5_DAY,
    //店长驳回
    MANAGER_BILL_REJECT,
    //POS驳回
    POS_BILL_REJECT,
    //POS产生市调单
    POS_BILL_CREATE
}
