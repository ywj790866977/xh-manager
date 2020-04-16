package com.xh.core.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * js-rexdu Feign配置 使用FeignClient进行服务间调用，传递headers信息
 */
@Configuration
public class KkCloudFeignConfig implements RequestInterceptor {

	public final static Logger logger = LoggerFactory.getLogger(KkCloudFeignConfig.class);

	public static ThreadLocal<String> dataToken = new ThreadLocal<>();

	@Override
	public void apply(RequestTemplate requestTemplate) {

		Object token = dataToken.get();

		if (token != null && StringUtils.isNotBlank((String) token)) {
			requestTemplate.header("TB-TOKEN", (String)token);// 设置TOKEN
		}
		
		//使用后清除掉
		dataToken.remove();

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return;
		}
		HttpServletRequest request = attributes.getRequest();
		if (request == null) {
			return;
		}

		// 添加token
		requestTemplate.header("TB-CLIENT-TYPE", request.getHeader("TB-CLIENT-TYPE"));
		requestTemplate.header("TB-TOKEN", request.getHeader("TB-TOKEN"));
		requestTemplate.header("TB-UUID", request.getHeader("TB-UUID"));
		requestTemplate.header("TB-VERSION", request.getHeader("TB-VERSION"));
		requestTemplate.header("TB-SITE-ID", request.getHeader("TB-SITE-ID"));
	}
}
