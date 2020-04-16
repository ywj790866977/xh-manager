package com.xh.core.common;

import com.xh.core.exception.ServiceException;
import com.xh.core.response.ServiceStatusCode;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class ServiceAssert {

    public static ServiceException serviceException(int code, String msg) {
    	return new ServiceException(code, msg);
    }

    public static ServiceException serviceException(int code, String msg, Object data) {
        return new ServiceException(code, msg, data);
    }

    public static ServiceException serviceException(ServiceStatusCode status, Object data) {
        return new ServiceException(status, data);
    }

    public static ServiceException serviceException(ServiceStatusCode status) {
        return new ServiceException(status);
    }

    public static void isFalse(boolean expression, int code, String msg) {
        if (!expression) {
            throw serviceException(code, msg);
        }
    }
    public static void isFalse(boolean expression, int code, String msg, Object data) {
        if (!expression) {
            throw serviceException(code, msg, data);
        }
    }
    public static void isFalse(boolean expression, ServiceStatusCode status) {
        if (!expression) {
            throw serviceException(status);
        }
    }
    public static void isFalse(boolean expression, ServiceStatusCode status, Object data) {
        if (!expression) {
            throw serviceException(status, data);
        }
    }

    public static void isFalse(Object object, Integer code, String msg) {
        if (object != null) {
            throw serviceException(code, msg);
        }
    }

    public static void isNull(Object object, Integer code, String msg) {
        if (object == null) {
            throw serviceException(code, msg);
        }
    }
    public static void isNull(Object object, Integer code, String msg, Object data) {
        if (object == null) {
            throw serviceException(code, msg, data);
        }
    }
    public static void isNull(Object object, ServiceStatusCode status) {
        if (object == null) {
            throw serviceException(status);
        }
    }
    public static void isNull(Object object, ServiceStatusCode status, Object data) {
        if (object == null) {
            throw serviceException(status, data);
        }
    }

    public static void isBlank(String str, Integer code, String msg) {
    	if (StringUtils.isBlank(str)) {
            throw serviceException(code, msg);
        }
    }
    public static void isBlank(String str, Integer code, String msg, Object data) {
    	if (StringUtils.isBlank(str)) {
            throw serviceException(code, msg, data);
        }
    }

    public static void isEmpty(Object object, Integer code, String msg) {
    	if (ObjectUtils.isEmpty(object)) {
            throw serviceException(code, msg);
        }
    }
    public static void isEmpty(Object object, Integer code, String msg, Object data) {
    	if (ObjectUtils.isEmpty(object)) {
            throw serviceException(code, msg, data);
        }
    }
    public static void isEmpty(Object object, ServiceStatusCode status) {
    	if (ObjectUtils.isEmpty(object)) {
            throw serviceException(status);
        }
    }
    public static void isEmpty(Object object, ServiceStatusCode status, Object data) {
    	if (ObjectUtils.isEmpty(object)) {
            throw serviceException(status, data);
        }
    }
}