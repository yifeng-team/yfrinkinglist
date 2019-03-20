package com.yfdyf.recommend.web.controller;

import com.yfdyf.recommend.service.IScoreApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认可卡 Controller
 * */
@RequestMapping("/scoreapprove")
@RestController
public class ScoreApproveController {

    @Autowired
    private IScoreApproveService iScoreApproveService;//认可卡

    @RequestMapping("getScoreApproveData")
    public void getScoreApproveData(){//调用接口获取认可卡数量 并存入数据库
        iScoreApproveService.getScoreApproveData();
    }

}
