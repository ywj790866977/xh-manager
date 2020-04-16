package com.xh.core.exception;

import com.alibaba.fastjson.JSON;
import com.xh.core.response.ResponseVO;
import com.xh.core.response.ServiceStatusCode;

/**
 * @author rubyle
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 2305201073664000499L;
    /**
     * 异常状态码
     */
    private int code;
    /**
     * 异常文字信息（也可自定有）
     */
    private String msg;
    /**
     * 异常自定义信息
     */
    private Object data;

    /**
     * @param httpStatus 返回状态码对象
     */
    public ServiceException(ServiceStatusCode httpStatus) {
        this.code = httpStatus.getCode();
        this.msg = httpStatus.getMsg();
    }

    /**
     *
     * @param httpStatus
     * @param data
     * @param <T>
     */
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
        return new ResponseVO<>(code, msg, data);
    }

    public ResponseVO<Object> toResponseVO(ServiceException se) {
        return new ResponseVO<>(se.getCode(), se.getMsg(), se.getData());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(toResponseVO());
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}