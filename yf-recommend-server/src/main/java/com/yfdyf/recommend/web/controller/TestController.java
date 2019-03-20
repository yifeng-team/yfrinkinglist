package com.yfdyf.recommend.web.controller;

import com.yfdyf.recommend.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping("hello")
    public String hello(){
        System.out.println("Hello World!");
        return "index";
    }

    @RequestMapping("test")
    public String test(){
        return testService.Test();
    }

    @Scheduled(cron = "0 00 3 * * ?")
    public void acceptCardTask(){
        //每天三点调用
        testService.Test();
    }
}
