package com.yfdyf.recommend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yfdyf.recommend.entity.JsonBean.AcceptCardJson;
import com.yfdyf.recommend.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public String Test() {

        String listUrl = "http://zzdemo.yfdyf.cn/yffk/mobile/eapprovalcard/getIroid";
        JSONArray data = restTemplate.getForObject(listUrl, JSONArray.class);
        //System.out.println(data);

        for (AcceptCardJson a : data.toJavaList(AcceptCardJson.class)){
            System.out.println(a);
        }

        return "true";
    }
}
