package com.xh.core.common;

import com.alibaba.fastjson.JSON;
import com.xh.core.response.ResponseHelper;
import com.xh.core.response.ResponseVO;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import jdk.nashorn.internal.ir.CallNode.EvalArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author rubyle
 */
@ControllerAdvice
@Slf4j
public class RestReturnWrapper implements ResponseBodyAdvice<Object> {

	private static final String SWAGGER_MED = "getDocumentation";
	/**
	 * 判定哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
	 */
	@Override
	public boolean supports(MethodParameter methodParameter,
			Class<? extends HttpMessageConverter<?>> converterType) {
		//获取当前处理请求的controller的方法
		//String methodName = methodParameter.getMethod().getName();
		// 拦/不拦截处理返回值的方法，如登录
		//String method = "login";
		//这里可以加入很多判定，如果在白名单的List里面，是否拦截
		return true;
	}


	/**
	 * 返回前对body，request，response等请求做处理
	 *
	 * @param body                 内容
	 * @param methodParameter      方法参数
	 * @param mediaType            类型
	 * @param httpMessageConverter 不知道
	 * @param serverHttpRequest    req
	 * @param serverHttpResponse   resp
	 * @return 包装后的响应
	 */
	@Override
	public Object beforeBodyWrite(Object body,
			MethodParameter methodParameter,
			MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> httpMessageConverter,
			ServerHttpRequest serverHttpRequest,
			ServerHttpResponse serverHttpResponse) {
		//具体返回值处理
		//如果返回的body为null
		String name = methodParameter.getMethod().getName();
		String type = mediaType.getType();
		log.info("【RestReturnWrapper】 type: {}",type);
		log.info("【RestReturnWrapper】 方法名:{}",name);
		if(SWAGGER_MED.equals(name) || "handle".equals(name)){
			// 解决swagger gateway 获取失败
			log.info("【RestReturnWrapper】 直接返回body");
			return body;
		}
		if (body == null) {
			log.info("【RestReturnWrapper】 body为null");
			return ResponseHelper.success();
		}
		// 文件上传下载，不需要改动，直接返回
		if (body instanceof Resource) {
			log.info("【RestReturnWrapper】 body为Resource");
			return body;
		}
		// 是字符串转为json
		if (body instanceof String) {
			log.info("【RestReturnWrapper】 body为String");
			return ResponseHelper.success(body);
		}
		// 如果已经封装成RestReturn,直接return
		if (body instanceof ResponseVO) {
			log.info("【RestReturnWrapper】 body为ResponseVO");
			return body;
		}
		// 非字符串非统一格式的返回，需要统一格式
		log.info("【RestReturnWrapper】 无匹配返回");
		return ResponseHelper.success(body);
	}
}