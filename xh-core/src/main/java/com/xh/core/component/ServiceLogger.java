//package com.xh.core.component;
//
//import com.alibaba.fastjson.JSONObject;
//import com.xh.core.component.es.ElasticSearchService;
//import com.xh.core.user.SessionUser;
//import com.xh.core.user.UserContext;
//import com.xh.core.utils.KKCloudUtils;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.stereotype.Component;
//
///**
// * @description: 日志统一发送接口
// * @author: js-reke
// * @create: 2019-11-14 17:43
// */
//@Component
//@ConditionalOnBean(ElasticSearchService.class)
//public class ServiceLogger {
//
//	private static final Logger log = LoggerFactory.getLogger(ServiceLogger.class);
//
//	@Resource
//	private ElasticSearchService searchService;
//
//	@Resource
//	private UserContext userContext;
//
//	public static final String SYSTEM_LOG_TOPIC = "systemLog";
//	public static final String LOGIN_LOG_TOPIC = "loginLog";
//	public static final String LOGOUT_LOG_TOPIC = "logoutLog";
//
//	@Resource
//	private ApplicationKeyGenerate keyGenerate;
//
//	/**
//	 * 日志发送方法
//	 *
//	 * @param name 索引
//	 * @param data 消息封装类
//	 */
//	public void logger(String name, Object data) {
//		logger(null, name, data);
//	}
//
//	public void logger(String appName, String name, Object data) {
//		JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));
//		JSONObject result = addLogSupplementInfo(jsonObject);
//		searchService.insertApp(appName, name, result);
//	}
//
//	public void financeLogger(String name, Object data) {
//		JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));
//		JSONObject result = addLogSupplementInfo(jsonObject);
//		searchService.financeInsert(name, result);
//	}
//
//	/**
//	 * 日志发送方法
//	 *
//	 * @param name 索引
//	 * @param data 消息封装类
//	 */
//	public void batchLogger(String name, List<Object> data) {
//		batchLogger(null, name, data);
//	}
//
//	public void batchLogger(String appName, String name, List<Object> data) {
//		List<JSONObject> list = new ArrayList<>(data.size());
//		for (int i = 0; i < data.size(); i++) {
//			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data.get(i)));
//			JSONObject result = addLogSupplementInfo(jsonObject);
//			list.add(result);
//		}
//		searchService.batchInsertApp(appName, name, list);
//	}
//
//	public void batchFinanceLogger(String name, List<Object> data) {
//		List<JSONObject> list = new ArrayList<>(data.size());
//		for (int i = 0; i < data.size(); i++) {
//			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data.get(i)));
//			JSONObject result = addLogSupplementInfo(jsonObject);
//			list.add(result);
//		}
//		searchService.batchFinanceInsert(name, list);
//	}
//
//	/**
//	 * 日志查询方法(财务)
//	 *
//	 * @param name          索引
//	 * @param sourceBuilder 查询条件封装类
//	 * @param clazz         泛型类型
//	 */
//	public <T> List<T> financeQuery(String name, SearchSourceBuilder sourceBuilder, Class<T> clazz) {
//		return searchService.financeSearch(name, sourceBuilder, clazz);
//	}
//
//	/**
//	 * 日志查询方法
//	 *
//	 * @param name          索引
//	 * @param sourceBuilder 查询条件封装类
//	 * @param clazz         泛型类型
//	 */
//	public <T> List<T> query(String name, SearchSourceBuilder sourceBuilder, Class<T> clazz) {
//		return query(null, name, sourceBuilder, clazz);
//	}
//
//	public <T> List<T> query(
//			String appName, String name, SearchSourceBuilder sourceBuilder, Class<T> clazz) {
//		return searchService.searchApp(appName,name, sourceBuilder, clazz);
//	}
//
//	/**
//	 * 补充日志信息
//	 *
//	 * @param jsonObject 需要补充的对象
//	 * @return 返回补充完成后的对象
//	 */
//	private JSONObject addLogSupplementInfo(JSONObject jsonObject) {
//		try {
//			if (userContext != null) {
//				HttpServletRequest httpRequest = UserContext.getHttpRequest();
//				if (httpRequest != null) {
//					SessionUser sessionUser = userContext.getUser();
//					if (sessionUser != null) {
//						jsonObject.put("userId", sessionUser.getId());
//						jsonObject.put("userName", sessionUser.getUserName());
//					}
//					jsonObject.put("insertData",
//							LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//					jsonObject.put("userAgent", httpRequest.getHeader("User-Agent"));
//					jsonObject.put("userIp", KKCloudUtils.getRemoteAddr());
//				}
//			}
//
//			return jsonObject;
//		} catch (Exception e) {
//			log.error("填充日志补充数据，出现异常！异常信息[{}]", e);
//		}
//		return jsonObject;
//	}
//}
