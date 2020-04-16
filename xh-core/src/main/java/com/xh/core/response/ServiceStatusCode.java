package com.xh.core.response;

import org.apache.commons.lang3.StringUtils;

public class ServiceStatusCode {
	private final int code;
	private final String msg;

	public ServiceStatusCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return this.code;
	}
	public String getMsg() {
		return this.msg;
	}

	@Override
	public String toString() {
		return StringUtils.join(this.code, ": ", this.msg);
	}

}
