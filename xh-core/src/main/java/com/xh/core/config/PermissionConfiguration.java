//package com.xh.core.config;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.xh.core.user.UserContext;
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class PermissionConfiguration implements WebMvcConfigurer {
//
//	private static final String DEF_INTERCEPT_ALLEXCLUSIONS = "/actuator/**"; // split by "," OR ";"
//
//	private static final String DEF_INTERCEPT_EXCLUSIONS = "/internal/**"; // split by "," OR ";"
//
//	@Resource
//	UserContext userContext;
//
//	@Value("${server.permission.path.exclusions:}")
//	private String exclude;
//
//	@Value("${server.servlet.context-path:}")
//	private String contextPath;
//
//	@Value("${server.permission.path.allExclusions:}")
//	private String allExclude;
//
//	private String[] exclusions;
//
//	private String[] allExclusions;
//
//	@PostConstruct
//	public void init() {
//		allExclude = DEF_INTERCEPT_ALLEXCLUSIONS + "," + allExclude;
//		allExclusions = allExclude.trim().split("[ ,;]+");
//		for (int i = 0; i < allExclusions.length; i++) {
//			if (allExclusions[i].startsWith("/") && contextPath.length() > 0) {
//				allExclusions[i] = contextPath + allExclusions[i].trim();
//			}
//		}
//
//
//		exclude = DEF_INTERCEPT_EXCLUSIONS + "," + exclude;
//		//exclude = StringUtils.isBlank(exclude) ? "": exclude;
//		exclusions = exclude.trim().split("[ ,;]+");
//		for (int i = 0; i < exclusions.length; i++) {
//			if (exclusions[i].startsWith("/") && contextPath.length() > 0) {
//				exclusions[i] = contextPath + exclusions[i].trim();
//			}
//		}
//
//	}
//
//	public String[] getExclusions() {
//		return exclusions;
//	}
//
//	@Bean
//	public FilterRegistrationBean<PermissionFilter> ssoFilterRegistration() {
//		FilterRegistrationBean<PermissionFilter> filter = new FilterRegistrationBean<PermissionFilter>();
//		filter.setName("ssoFilter");
//		filter.setFilter(new PermissionFilter(exclusions, userContext));
//		filter.setOrder(-1);
//		filter.addUrlPatterns("/*");
//		return filter;
//	}
//
//	@Bean
//	public HttpMessageConverters fastJsonHttpMessageConverters() {
//		// 1、定义一个convert转换消息的对象
//		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//
//		// 2、添加MediaTypes配置
//		List<MediaType> fastMediaTypes = new ArrayList<>();
//		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//		fastConverter.setSupportedMediaTypes(fastMediaTypes);
//
//		// 3、添加fastjson的配置信息
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		// fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
//		// SerializerFeature.DisableCircularReferenceDetect);
//
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
//				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect,
//				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteDateUseDateFormat,
//				SerializerFeature.IgnoreNonFieldGetter);
//
//		fastConverter.setFastJsonConfig(fastJsonConfig);
//
//		return new HttpMessageConverters(fastConverter);
//	}
//
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(mappingJackson2HttpMessageConverter());
//	}
//
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		ObjectMapper objectMapper = new ObjectMapper();
//		// 添加此配置
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		converter.setObjectMapper(objectMapper);
//		return converter;
//	}
//}