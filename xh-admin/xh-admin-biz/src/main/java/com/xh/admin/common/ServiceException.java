package com.xh.admin.common;

import com.alibaba.fastjson.JSON;

/**
 * @author rubyle
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 2305201073664000499L;
    private int code;
    private String msg;
    private Object data;

    public ServiceException(ServiceStatusCode httpStatus) {
        this.code = httpStatus.getCode();
        this.msg = httpStatus.getMsg();
    }

    public <T> ServiceException(ServiceStatusCode httpStatus, T data) {
        this.code = httpStatus.getCode();
        this.msg = httpStatus.getMsg();
        this.data = data;
    }

    public ServiceException(int status, String message) {
        this.code = status;
        this.msg = message;
    }

    public <T> ServiceException(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVO<Object> toResponseVO() {
        return new ResponseVO(this.code, this.msg, this.data);
    }

    public ResponseVO<Object> toResponseVO(ServiceException se) {
        return new ResponseVO(se.getCode(), se.getMsg(), se.getData());
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this.toResponseVO());
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}