package com.yfdyf.recommend.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yfdyf.recommend.web.result.StatusMsg;

/**
 * 返回统一响应的JSON结果
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    //请求是否成功
    private boolean success;
    //数据 泛型
    private T data;
    //返回消息
    private String msg;
    //状态
    private Integer status = 200;

    public static final Integer ERROR_STATUS = 599;

    public static ResponseResult success(Object data) {
        return new ResponseResult(true,  data);
    }

    public static ResponseResult success() {
        return new ResponseResult(true,  null);
    }

    public static ResponseResult error(String msg) {
        return new ResponseResult(false,  msg, ERROR_STATUS);
    }

    public static ResponseResult error(StatusMsg statusMsg) {
        return new ResponseResult(false,  statusMsg.getMsg(), statusMsg.getStatus());
    }

    public ResponseResult(boolean success, T data, Integer status) {
        this.success = success;
        if (success){
            this.data = data;
        }
        this.status = status;
    }

    public ResponseResult(boolean success, String msg, Integer status) {
        this.success = success;
        this.msg = msg;
        this.status = status;
    }


    public ResponseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public ResponseResult(boolean success, String error) {
        this.success = success;
        this.msg = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String error) {
        this.msg = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
