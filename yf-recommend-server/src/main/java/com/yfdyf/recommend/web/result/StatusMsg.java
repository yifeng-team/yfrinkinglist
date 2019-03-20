package com.yfdyf.recommend.web.result;

import lombok.Data;

@Data
public class StatusMsg {

    //通用的错误码
    public static StatusMsg SUCCESS = new StatusMsg(200, "success");
    public static StatusMsg PERSONCODE_NOT_EMPTY = new StatusMsg(500100, "人员编码不能为空");
    public static StatusMsg RECOMMENDID_NOT_EMPTY = new StatusMsg(500100, "推荐详情ID不能为空");
    public static StatusMsg RECOMMENDID_NOT_EXIST = new StatusMsg(500100, "对应的推荐信息不存在");
    public static StatusMsg PERSONCODE_ERROR = new StatusMsg(500100, "人员编码信息有误");
    public static StatusMsg RECOMMENDID_ERROR = new StatusMsg(500100, "推荐详情ID号不存在");
    public static StatusMsg REPLY_STATUS_ERROR = new StatusMsg(500100, "回复状态与当前状态数据一致");
    public static StatusMsg REPLY_NOT_EMPTY_ERROR = new StatusMsg(500100, "已淘汰原因必填");
    public static StatusMsg PERSON_NOT_EMPTY_ERROR = new StatusMsg(500100, "入职工号必填");
    public static StatusMsg BONUS_NOT_EMPTY_ERROR = new StatusMsg(500100, "推荐奖金不能为空");
    public static StatusMsg HIRED_STATUS_ERROR = new StatusMsg(500100, "当前员工已经入职");
    public static StatusMsg ADMIN_RIGHT_ERROR = new StatusMsg(500100, "管理员才有权限");
    public static StatusMsg NO_RIGHT_ERROR = new StatusMsg(500100, "你无权限查看此数据");
    public static StatusMsg COMPANY_RIGHT_ERROR = new StatusMsg(500100, "对应分公司管理员才能处理此数据");
    public static StatusMsg ENTRY_PERSON_CODE_ERROR = new StatusMsg(500100, "入职工号未找到,请确认该人员工号信息");
    public static StatusMsg PARAM_ERROR = new StatusMsg(500100, "参数不能为空");
    public static StatusMsg BIND_ERROR = new StatusMsg(500101, "参数校验异常：%s");
    public static StatusMsg DEFAULT_BIND_ERROR = new StatusMsg(500102, "请检查参数输入");
    public static StatusMsg MAX_FILE_ERROR = new StatusMsg(500102, "文件最大上传10M");
    public static StatusMsg UPLOAD_FILE_ERROR = new StatusMsg(500102, "文件上传处理错误");
    public static StatusMsg SERVER_ERROR = new StatusMsg(500101, "接口处理错误");
    public static StatusMsg STATUS_ERROR = new StatusMsg(500101, "错误的单据状态");

    public static StatusMsg BILLCODE_NOT_EMPTY = new StatusMsg(500100, "区域市调编码不能为空");
    public static StatusMsg DISTRIBUTE_NOT_EMPTY = new StatusMsg(500100, "分配人工号不能为空");
    public static StatusMsg EMPBILLCODE_NOT_EMPTY = new StatusMsg(500100, "员工市调编码不能为空");

    public static StatusMsg UUID_NOT_EMPTY = new StatusMsg(500100, "唯一标识不能为空");
    public static StatusMsg TARGET_NOT_EMPTY = new StatusMsg(500100, "目标员工工号不能为空");
    private int status;
    private String msg;

    private StatusMsg() {
    }

    private StatusMsg(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 填充错误码以及参数
     * @param args
     * @return
     */
    public StatusMsg fillArgs(Object... args){
        int code = this.status;
        String message = String.format(this.msg, args);
        return new StatusMsg(code, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
