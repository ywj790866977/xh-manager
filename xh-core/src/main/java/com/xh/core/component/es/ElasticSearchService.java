//package com.xh.core.component.es;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.xh.core.response.Page;
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.Resource;
//import org.apache.commons.lang3.StringUtils;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
///**
// * @author Reke
// * @date 2019/11/13 16:54
// * @Description Es封装抽取类
// */
//
//@Component
//@ConditionalOnBean(EsClientSpringConfig.class)
//public class ElasticSearchService {
//
//	private static final Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);
//
//	@Resource
//	private RestHighLevelClient client;
//
//	@Resource
//	private ApplicationKeyGenerate appKeyGenerate;
//
//	private static final String ES_KEY_FINANCE = "kc_finance_";
//
//	/**
//	 * 统一索引封装
//	 *
//	 * @param index 自定有索引
//	 * @return 封装后索引
//	 */
//	private String setIndex(String index) {
//		return appKeyGenerate.getIndex(index);
//	}
//
//	public String getAppIndex(String appname, String index) {
//		return appKeyGenerate.getIndex(appname, index);
//	}
//
//	/**
//	 * 单条数据存储
//	 *
//	 * @param index  索引名称
//	 * @param entity 存储数据封装类
//	 */
//	public void insert(String index, Object entity) {
//		insertApp(null, index, entity);
//	}
//
//	/**
//	 * 单条数据存储
//	 *
//	 * @param index  索引名称
//	 * @param entity 存储数据封装类
//	 */
//	public void insertApp(String appName, String index, Object entity) {
//		IndexRequest request = null;
//		if (StringUtils.isEmpty(appName)) {
//			request = new IndexRequest(setIndex(index));
//		} else {
//			request = new IndexRequest(getAppIndex(appName, index));
//		}
//		try {
//			request.source(JSONObject.toJSONString(entity), XContentType.JSON);
//			IndexResponse responses = client.index(request, RequestOptions.DEFAULT);
//			int statusCode = responses.status().getStatus();
//			if (HttpStatus.CREATED.value() != statusCode) {
//				logger.error("es插入数据失败，返回状态码：[{}]", statusCode);
//			}
//		} catch (Exception e) {
//			logger.error("es插入数据失败，发生异常：[{}]", e.getMessage());
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 财务调用ES索引
//	 *
//	 * @param index
//	 * @param entity
//	 */
//	public void financeInsert(String index, Object entity) {
//		IndexRequest request = new IndexRequest(ES_KEY_FINANCE + index);
//		try {
//			request.source(JSONObject.toJSONString(entity), XContentType.JSON);
//			IndexResponse responses = client.index(request, RequestOptions.DEFAULT);
//			int statusCode = responses.status().getStatus();
//			if (HttpStatus.CREATED.value() != statusCode) {
//				logger.error("es插入数据失败，返回状态码：[{}]", statusCode);
//			}
//		} catch (Exception e) {
//			logger.error("es插入数据失败，发生异常：[{}]", e.getMessage());
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 将索引生成封装，并单条数据存储
//	 *
//	 * @param index  索引名称
//	 * @param entity 存储数据封装类
//	 */
//	public void insertByGenerateKey(String index, Object entity) {
//		IndexRequest request = new IndexRequest(setIndex(index));
//		try {
//			request.source(JSONObject.toJSONString(entity), XContentType.JSON);
//			IndexResponse responses = client.index(request, RequestOptions.DEFAULT);
//			int statusCode = responses.status().getStatus();
//			if (HttpStatus.CREATED.value() != statusCode) {
//				logger.error("es插入数据失败，返回状态码：[{}]", statusCode);
//			}
//		} catch (Exception e) {
//			logger.error("es插入数据失败，发生异常：[{}]", e.getMessage());
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 批量插入方法
//	 *
//	 * @param index 索引
//	 * @param list  存储数据封装类 列表
//	 */
//	public void batchInsert(String index, List<?> list) {
//		batchInsertApp(null, index, list);
//	}
//
//	public void batchFinanceInsert(String index, List<?> list) {
//		batchInsertApp(null, ES_KEY_FINANCE + index, list);
//	}
//
//
//	public void batchInsertApp(String appName, String index, List<?> list) {
//
//		BulkRequest request = new BulkRequest();
//		if (StringUtils.isEmpty(appName)) {
//			list.forEach(item -> request
//					.add(new IndexRequest(setIndex(index)).source(JSON.toJSONString(item), XContentType.JSON)));
//		} else {
//			list.forEach(item -> request
//					.add(new IndexRequest(getAppIndex(appName,index)).source(JSON.toJSONString(item), XContentType.JSON)));
//		}
//
//
//		list.forEach(item -> request
//				.add(new IndexRequest(setIndex(index)).source(JSON.toJSONString(item), XContentType.JSON)));
//		try {
//			BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
//			int statusCode = responses.status().getStatus();
//			if (HttpStatus.OK.value() != statusCode) {
//				logger.error("es批量插入数据失败，返回状态码：[{}]", statusCode);
//			}
//		} catch (Exception e) {
//			logger.error("es批量插入数据失败，发生异常：[{}]", e.getMessage());
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 将索引生成封装，并批量插入方法
//	 *
//	 * @param index 索引
//	 * @param list  存储数据封装类 列表
//	 */
//	public void batchInsertByGenerateKey(String index, List<Object> list) {
//		BulkRequest request = new BulkRequest();
//		list.forEach(item -> request
//				.add(new IndexRequest(setIndex(index)).source(JSON.toJSONString(item), XContentType.JSON)));
//		try {
//			BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
//			int statusCode = responses.status().getStatus();
//			if (HttpStatus.OK.value() != statusCode) {
//				logger.error("es批量插入数据失败，返回状态码：[{}]", statusCode);
//			}
//		} catch (Exception e) {
//			logger.error("es批量插入数据失败，发生异常：[{}]", e.getMessage());
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 查询方法
//	 *
//	 * @param index   索引
//	 * @param builder 查询参数封装类
//	 * @param clazz   返回值类类型
//	 * @param <T>     返回值类型泛型
//	 * @return 返回自定义类集合
//	 */
//	public <T> List<T> search(String index, SearchSourceBuilder builder, Class<T> clazz) {
//		return searchApp(null, index, builder, clazz);
//	}
//
//	/**
//	 * 分页查询方法
//	 *
//	 * @param index   索引
//	 * @param builder 查询参数封装类
//	 * @param clazz   返回值类类型
//	 * @param <T>     返回值类型泛型
//	 * @return 返回自定义类集合
//	 */
//	public <T> Page<T> searchPage(String index, SearchSourceBuilder builder, Class<T> clazz) {
//		return searchAppPage(null, index, builder, clazz);
//	}
//
//	public <T> List<T> financeSearch(String index, SearchSourceBuilder builder, Class<T> clazz) {
//		return searchFinanceApp(null, ES_KEY_FINANCE+index, builder, clazz);
//	}
//
//
//	/**
//	 * 查询方法
//	 *
//	 * @param index   索引
//	 * @param builder 查询参数封装类
//	 * @param clazz   返回值类类型
//	 * @param <T>     返回值类型泛型
//	 * @return 返回自定义类集合
//	 */
//	public <T> List<T> searchApp(String appName,
//			String index, SearchSourceBuilder builder, Class<T> clazz) {
//		SearchRequest searchRequest = null;
//
//		if (StringUtils.isEmpty(appName)) {
//			searchRequest = new SearchRequest(setIndex(index));
//		} else {
//			searchRequest = new SearchRequest(getAppIndex(appName,index));
//		}
//
//		searchRequest.source(builder);
//		try {
//			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//			if (response == null) {
//				logger.error("查询es为空，http response为空");
//			} else {
//				return getResponseBodyList(response, clazz);
//			}
//		} catch (Exception e) {
//			logger.error("查询es发生异常", e);
//		}
//		return new ArrayList<>();
//	}
//
//	/**
//	 * 分页查询查询方法
//	 *
//	 * @param index   索引
//	 * @param builder 查询参数封装类
//	 * @param clazz   返回值类类型
//	 * @param <T>     返回值类型泛型
//	 * @return 返回自定义类集合
//	 */
//	public <T> Page<T> searchAppPage(String appName, String index, SearchSourceBuilder builder, Class<T> clazz) {
//		SearchRequest searchRequest = null;
//
//		if (StringUtils.isEmpty(appName)) {
//			searchRequest = new SearchRequest(setIndex(index));
//		} else {
//			searchRequest = new SearchRequest(getAppIndex(appName,index));
//		}
//
//		searchRequest.source(builder);
//		try {
//			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//			if (response == null) {
//				logger.error("查询es为空，http response为空");
//			} else {
//				return getResponseBodyListByPage(response, clazz);
//			}
//		} catch (Exception e) {
//			logger.error("查询es发生异常", e);
//		}
//		return new Page<>();
//	}
//
//	/**
//	 * 查询方法
//	 *
//	 * @param index   索引
//	 * @param builder 查询参数封装类
//	 * @param clazz   返回值类类型
//	 * @param <T>     返回值类型泛型
//	 * @return 返回自定义类集合
//	 */
//	public <T> List<T> searchFinanceApp(String appName, String index, SearchSourceBuilder builder, Class<T> clazz) {
//		SearchRequest searchRequest = null;
//
//		if (StringUtils.isEmpty(appName)) {
//			searchRequest = new SearchRequest(index);
//		} else {
//			searchRequest = new SearchRequest(appName,index);
//		}
//
//		searchRequest.source(builder);
//		try {
//			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//			if (response == null) {
//				logger.error("查询es为空，http response为空");
//			} else {
//				return getResponseBodyList(response, clazz);
//			}
//		} catch (Exception e) {
//			logger.error("查询es发生异常", e);
//		}
//		return new ArrayList<>();
//	}
//
//
//	/**
//	 * 单个数据查询方法
//	 *
//	 * @param index   缩影名称
//	 * @param builder 查询条件封装类
//	 * @return 单个json对象
//	 */
//	public JSONObject search(String index, SearchSourceBuilder builder) {
//		SearchRequest searchRequest = new SearchRequest(setIndex(index));
//		searchRequest.source(builder);
//		try {
//			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//			if (response == null) {
//				logger.error("查询es失败，http response为空");
//			} else {
//				boolean flag = getHttpStatusCode(response);
//				if (flag) {
//					return getResponseBodyJson(response);
//				}
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return null;
//	}
//
//	public JSONObject searchApp(String appName, String index, SearchSourceBuilder builder) {
//
//		SearchRequest searchRequest = null;
//		if (StringUtils.isEmpty(appName)) {
//			searchRequest = new SearchRequest(setIndex(index));
//		} else {
//			searchRequest = new SearchRequest(getAppIndex(appName,index));
//		}
//
//		searchRequest.source(builder);
//		try {
//			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
//			if (response == null) {
//				logger.error("查询es失败，http response为空");
//			} else {
//				boolean flag = getHttpStatusCode(response);
//				if (flag) {
//					return getResponseBodyJson(response);
//				}
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return null;
//	}
//
//	/**
//	 * 解析请求返回值状态
//	 *
//	 * @param response 请求返回值对象
//	 * @return 返回请求是否成功 true成功， false失败
//	 * @author js-Reke
//	 * @date 2019/11/13 17:31
//	 */
//	private boolean getHttpStatusCode(SearchResponse response) {
//		int code = response.status().getStatus();
//		if (code == HttpStatus.OK.value()) {
//			return true;
//		} else {
//			logger.error("查询es失败，返回状态码为：{}", code);
//		}
//		return false;
//	}
//
//	/**
//	 * 解析为类对象集合
//	 *
//	 * @param response 返回值对象
//	 * @param clazz    自定义类对象
//	 * @param <T>      泛型类对象
//	 * @return 返回类对象集合
//	 */
//	private <T> List<T> getResponseBodyList(SearchResponse response, Class<T> clazz) {
//		SearchHit[] hits = response.getHits().getHits();
//		if (hits != null) {
//			List<T> res = new ArrayList<>(hits.length);
//			for (SearchHit hit : hits) {
//				res.add(JSON.parseObject(hit.getSourceAsString(), clazz));
//			}
//			return res;
//		} else {
//			logger.error("查询es失败，返回值为空");
//		}
//		return null;
//	}
//
//	/**
//	 * 解析为类对象集合
//	 *
//	 * @param response 返回值对象
//	 * @param clazz    自定义类对象
//	 * @param <T>      泛型类对象
//	 * @return 返回类对象集合
//	 */
//	private <T> Page<T> getResponseBodyListByPage(SearchResponse response, Class<T> clazz) {
//		SearchHit[] hits = response.getHits().getHits();
//		Page<T> pages = new Page<>();
//		if (hits != null) {
//			List<T> res = new ArrayList<>(hits.length);
//			for (SearchHit hit : hits) {
//				res.add(JSON.parseObject(hit.getSourceAsString(), clazz));
//			}
//			pages.setList(res);
//			pages.setTotal(response.getHits().getTotalHits().value);
//			return pages;
//		} else {
//			logger.error("查询es失败，返回值为空");
//		}
//		return null;
//	}
//
//	private JSONObject getResponseBodyJson(SearchResponse response) {
//		SearchHit[] hits = response.getHits().getHits();
//		if (hits != null) {
//			for (SearchHit hit : hits) {
//				return JSON.parseObject(hit.getSourceAsString());
//			}
//		} else {
//			logger.error("查询es失败，返回值为空");
//		}
//		return null;
//	}
//}
