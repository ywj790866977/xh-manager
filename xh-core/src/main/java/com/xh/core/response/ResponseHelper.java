package com.xh.core.response;

import com.xh.core.exception.ServiceException;

/**
 * 响应处理
 *
 * @author xxx
 * 2019年1月10日
 */
public class ResponseHelper {

	public final static void execption(ServiceStatusCode httpStatus) {
		throw new ServiceException(httpStatus);
	}
	public final static void execption(ServiceStatusCode httpStatus, Object data) {
		throw new ServiceException(httpStatus, data);
	}

	public final static void execption(int status, String message) {
		throw new ServiceException(status, message);
	}
	public final static Object execption(int status, String message, Object data) {
		throw new ServiceException(status, message, data);
	}

	/**
	 * 服务端处理成功
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> success() {
		return new ResponseVO<T>(CommonStatusCode.SUCCESS);
	}

	/**
	 * 服务端处理成功
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> success(T data) {
		return new ResponseVO<T>(data);
	}

	/**
	 * 服务端数据校验失败（默认）
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> badRequest() {
		return new ResponseVO<T>(CommonStatusCode.FAILURE);
	}

	/**
	 * 服务端校验失败（请求数据语义有误）
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> badRequest(T data) {
		return new ResponseVO<T>(CommonStatusCode.FAILURE, data);
	}

	/**
	 * 服务端校验失败（请求数据语义有误）
	 * @param message
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> badRequest(String message) {
		return new ResponseVO<T>(CommonStatusCode.FAILURE.getCode(), message);
	}

	/**
	 * 服务端校验数据冲突（默认）
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> conflict() {
		return new ResponseVO<T>(CommonStatusCode.CONFLICT);
	}

	/**
	 * 服务端校验数据冲突（数据重复...）
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> conflict(T data) {
		return new ResponseVO<T>(CommonStatusCode.CONFLICT, data);
	}

	/**
	 * 服务端校验数据冲突（数据重复...）
	 * @param message
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> conflict(String message) {
		return new ResponseVO<T>(CommonStatusCode.CONFLICT.getCode(), message);
	}

	/**
	 * 服务端内部错误
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> error() {
		return new ResponseVO<T>(CommonStatusCode.FAILURE);
	}

	/**
	 * 服务端内部错误
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> error(T data) {
		return new ResponseVO<T>(CommonStatusCode.FAILURE, data);
	}

	/**
	 * 服务端内部错误
	 * @param message
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> error(String message) {
		return new ResponseVO<T>(CommonStatusCode.FAILURE.getCode(), message);
	}

	public final static <T> ResponseVO<T> error(String message,String errorSn) {
		return new ResponseVO<T>(CommonStatusCode.FAILURE.getCode(), message,null,errorSn);
	}

	/**
	 * 无权限
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> unauthorized() {
		return new ResponseVO<T>(CommonStatusCode.UNAUTHORIZED);
	}

	/**
	 * 无权限
	 * @param <T>
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> unauthorized(T data) {
		return new ResponseVO<T>(CommonStatusCode.UNAUTHORIZED, data);
	}

	/**
	 * 无权限
	 * @param message
	 * @return ResponseVO
	 */
	public final static <T> ResponseVO<T> unauthorized(String message) {
		return new ResponseVO<T>(CommonStatusCode.UNAUTHORIZED.getCode(), message);
	}
}