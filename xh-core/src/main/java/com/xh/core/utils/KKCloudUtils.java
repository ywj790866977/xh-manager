package com.xh.core.utils;

import com.xh.core.common.ServiceAssert;
import com.xh.core.response.CommonStatusCode;
import com.xh.core.user.UserContext;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

public class KKCloudUtils {
	private static int localOffsetHours = 8;

	public static final DateTime getDateTimeGMT8() {
		return LocalDateTime.now().toDateTime(DateTimeZone.forOffsetHours(localOffsetHours));
	}

	public static String getRemoteAddr() {
		HttpServletRequest request = UserContext.getHttpRequest();
		ServiceAssert.isNull(request, CommonStatusCode.FAILURE);

		String remoteAddr = request.getHeader("x-forwarded-for");
        if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }
        if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getHeader("HTTP_CLIENT_IP");
        }
        if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (remoteAddr == null || remoteAddr.length() == 0 || "unknown".equalsIgnoreCase(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        return remoteAddr;
    }


	public static void setLocalOffsetHours(int offsetHours) {
		localOffsetHours = offsetHours;
	} 
}
