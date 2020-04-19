package com.xh.gateway.utils;


import com.xh.gateway.constant.CommonConstants;
import com.xh.gateway.constant.ErrorCode;
import com.xh.gateway.constant.enums.CommonErrorCode;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public static <T> ResponseResult<T> success() {
        return restResult(null, CommonConstants.SUCCESS, CommonConstants.SUCCESS_MSG);
    }

    public static <T> ResponseResult<T> success(T data) {
        return restResult(data, CommonConstants.SUCCESS, CommonConstants.SUCCESS_MSG);
    }

    public static <T> ResponseResult<T> success(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> ResponseResult<T> success(String msg) {
        return restResult(null, CommonConstants.SUCCESS, msg);
    }

    public static <T> ResponseResult<T> failed() {
        return failed(CommonErrorCode.EXCEPTION);
    }

    public static <T> ResponseResult<T> failed(Integer code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> ResponseResult<T> failed(Integer code, T data, String msg) {
        return restResult(data, code, msg);
    }

    public static <T> ResponseResult<T> failed(T data, ErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> ResponseResult<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> ResponseResult<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, CommonConstants.FAIL_MSG);
    }

    public static <T> ResponseResult<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    public static <T> ResponseResult<T> failed(ErrorCode code) {
        return restResult(null, code.getCode(), code.getMessage());
    }

    private static <T> ResponseResult<T> restResult(T data, int code, String msg) {
        ResponseResult<T> apiResult = new ResponseResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}

