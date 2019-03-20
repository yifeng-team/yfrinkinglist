package com.yfdyf.recommend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yfdyf.recommend.dao.BaseDao.InterfaceRecordMapper;
import com.yfdyf.recommend.dao.BaseDao.ScoreApproveMapper;
import com.yfdyf.recommend.dao.CommonDao.InterfaceRecordMapperImpl;
import com.yfdyf.recommend.dao.CommonDao.ScoreApproveMapperImpl;
import com.yfdyf.recommend.entity.BaseBean.InterfaceRecord;
import com.yfdyf.recommend.entity.BaseBean.ScoreApprove;
import com.yfdyf.recommend.entity.JsonBean.AcceptCardJson;
import com.yfdyf.recommend.service.IScoreApproveService;
import com.yfdyf.recommend.tool.SystemDateFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 认可卡
 * */
@Component
public class ScoreApproveServiceImpl implements IScoreApproveService {

    private static Logger logger = Logger.getLogger(ScoreApproveServiceImpl.class);//日志

    @Autowired
    private RestTemplate restTemplate; //接口调用工具

    @Resource(type = ScoreApproveMapper.class)
    private ScoreApproveMapperImpl scoreApproveMapper;//认可卡

    @Resource(type = InterfaceRecordMapper.class)
    private InterfaceRecordMapperImpl interfaceRecordMapper;//接口调用记录

    @Override
    public void getScoreApproveData() { //获取认可卡数据

        String listUrl = "http://zzdemo.yfdyf.cn/yffk/mobile/eapprovalcard/getIroid";

        //String listUrl = " https://mdwx.yfdyf.com/yffk/mobile/eapprovalcard/getIroid";
        JSONArray data = restTemplate.getForObject(listUrl, JSONArray.class);
        logger.info(" 调用 "+listUrl);
        InterfaceRecord interfaceRecord = new InterfaceRecord();

        interfaceRecord.setInterfaceCode("认可卡接口");//接口code
        interfaceRecord.setUseDate(SystemDateFormat.getNowDate());//调用日期

        if(data != null){
            interfaceRecord.setUseState("成功");
            logger.info(" 成功! ");
        }else{
            interfaceRecord.setUseState("失败");
            logger.info(" 错误! ");
        }

        try{
            interfaceRecordMapper.insert(interfaceRecord);//存入接口调用日志表
        }catch (Exception e){
            logger.error(e);
        }

        for (AcceptCardJson a : data.toJavaList(AcceptCardJson.class)){//遍历

            //System.out.println(a);
            ScoreApprove scoreApprove = new ScoreApprove();
            scoreApprove.setPersonCode(a.getPERSON_ID());//人员id
            scoreApprove.setCountDate(SystemDateFormat.getNowDate());//统计年月日
            scoreApprove.setCountScore(a.getINTEGRAL());//认可卡积分

            try{
                scoreApproveMapper.insert(scoreApprove);//存入认可卡表
            }catch (Exception e){
                logger.error(e);
            }


        }


    }
}
