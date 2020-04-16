package com.xh.core.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 ** 框架标准数据返回格式
 * 
 * @author xxx 2019年1月25日
 * @param <T>
 */
public class ResponseVO<T> {

	/**
	 * 返回状态
	 */
	private int code;
	/**
	 * 描述信息
	 */
	private String msg;

	/**
	 * 错误序号
	 */
	private String errorSn;

	/**
	 * 数据
	 */
	private T data;

	public ResponseVO() {
	}

	public ResponseVO(ServiceStatusCode httpStatus) {
		this.code = httpStatus.getCode();
		this.msg = httpStatus.getMsg();
	}

	public ResponseVO(ServiceStatusCode httpStatus, T data) {
		this.code = httpStatus.getCode();
		this.msg = httpStatus.getMsg();
		this.data = data;
	}

	public ResponseVO(int status, String message) {
		this.code = status;
		this.msg = message;
	}

	public ResponseVO(int status, String message, T data) {
		this.code = status;
		this.msg = message;
		this.data = data;
	}
	
	public ResponseVO(int status, String message, T data, String errorSn) {
		this.code = status;
		this.msg = message;
		this.data = data;
		this.errorSn = errorSn;
	}

	public ResponseVO(T data) {
		this.code = CommonStatusCode.SUCCESS.getCode();
		this.msg = CommonStatusCode.SUCCESS.getMsg();
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorSn() {
		return errorSn;
	}

	public void setErrorSn(String errorSn) {
		this.errorSn = errorSn;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
	}
}