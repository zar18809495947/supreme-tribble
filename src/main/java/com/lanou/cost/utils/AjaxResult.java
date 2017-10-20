package com.lanou.cost.utils;

/**
 * Created by zar on 2017/10/10.
 */
public class AjaxResult {
    private String msg;
    private Integer errorCode;
    private Object data;

    public AjaxResult(String msg, Integer errorCode, Object data) {
        this.msg = msg;
        this.errorCode = errorCode;
        this.data = data;
    }

    public AjaxResult() {

    }

    public AjaxResult(Object data) {
        this.msg = "添加成功!";
        this.errorCode = 0;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "msg='" + msg + '\'' +
                ", errorCode=" + errorCode +
                ", data=" + data +
                '}';
    }
}
