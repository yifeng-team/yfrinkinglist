package com.yfdyf.recommend.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by YF on 2019/1/18.
 * 企业微信定时推送任务
 */
@Component
public class SendMessageTask {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    //private IAsyncService asyncService;

    /**
     * 每天早上9点推送 市调单距离截止日期还有五天的信息
     */
    @Scheduled(cron = "0 00 9 * * ?")
    public void sendMessageTaskBy5Day(){
       // logger.info("======start 员工市调单定时任务====== 发送距离截止日期5天内员工没完成市调单的定时任务");
       // List<MarketMessage> marketMessages = marketEmpPricershService.findMarketPersonByUntreatedDay();
       //asyncService.sendTextCardNotices(marketMessages, SendMessageType.TASK_OVER_TIME_5_DAY);
        // logger.info("======end 员工市调单定时任务====== 发送距离截止日期5天内员工没完成市调单的定时任务");

    }
}
