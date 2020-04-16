package com.xh.core.component;

import com.xh.core.config.support.DynamicDataSource;
import com.xh.core.user.UserContext;
import java.util.ArrayList;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author js-Reke
 * @date 2019/11/18 14:39
 * @Description 应用Key 封装类
 */
@Component
public class ApplicationKeyGenerate {
	
	public final static Logger logger = LoggerFactory.getLogger(ApplicationKeyGenerate.class);

	@Resource
	private UserContext userContext;

	/**
	 * 业务代码
	 */
	@Value("${spring.application.name:}")
	private String appName;

	/**
	 * key公共头
	 */
	private static final String KK_CLOUD_KEY_PREFIX = "kc";
	
	private static final String KK_CLOUD = "kc_cloud";

	/**
	 * key分割符
	 */
	private static final char REDIS_DELIMITER = ':';

	/**
	 * key分割符
	 */
	private static final char ES_DELIMITER = '_';

	/**
	 * key分割符
	 */
	private static final char TOPIC_DELIMITER = '_';

	/**
	 * 统一封装 key
	 * 
	 * @param key 自定义key
	 * @return 封装key列表
	 */
	public ArrayList<String> getKeyScope(String key) {
		ArrayList<String> keys = new ArrayList<>();
		keys.add(KK_CLOUD_KEY_PREFIX);
		//keys.add(String.valueOf(userContext.getSiteId()));
		keys.add(String.valueOf(DynamicDataSource.getSite()==null?userContext.getSiteId():DynamicDataSource.getSite()));
		keys.add(appName);
		if (StringUtils.isNotEmpty(key)) {
			keys.add(key);
		}
		return keys;
	}
	/**
	 * 统一封装 key
	 * 
	 * @param key 自定义key
	 * @return 封装key列表
	 */
	public ArrayList<String> getKeyScope(String appName, String key) {
		ArrayList<String> keys = new ArrayList<>();
		keys.add(KK_CLOUD_KEY_PREFIX);
		//keys.add(String.valueOf(userContext.getSiteId()));
		keys.add(String.valueOf(DynamicDataSource.getSite()==null?userContext.getSiteId():DynamicDataSource.getSite()));
		keys.add(appName);
		if (StringUtils.isNotEmpty(key)) {
			keys.add(key);
		}
		return keys;
	}

	/**
	 * 统一封装 key(不带站点)
	 * 
	 * @param key 自定义key
	 * @return 封装key列表
	 */
	public ArrayList<String> getAppKeyScope(String key) {
		ArrayList<String> keys = new ArrayList<>();
		keys.add(KK_CLOUD_KEY_PREFIX);
		keys.add(appName);
		if (StringUtils.isNotEmpty(key)) {
			keys.add(key);
		}
		return keys;
	}
	
	/**
	 * 统一封装 key
	 * 
	 * @param key 自定义key
	 * @return 封装key列表
	 */
	public ArrayList<String> getKeyCloudScope(String key) {
		ArrayList<String> keys = new ArrayList<>();
		keys.add(KK_CLOUD);
		if (StringUtils.isNotEmpty(key)) {
			keys.add(key);
		}

		return keys;
	}

	
	public ArrayList<String> getSiteKeyScope(String key) {
		ArrayList<String> keys = new ArrayList<>();
		keys.add(KK_CLOUD_KEY_PREFIX);
		//keys.add(String.valueOf(userContext.getSiteId()));
		keys.add(String.valueOf(DynamicDataSource.getSite()==null?userContext.getSiteId():DynamicDataSource.getSite()));
		if (StringUtils.isNotEmpty(key)) {
			keys.add(key);
		}
		return keys;
	}

	/**
	 * 统一封装 key
	 * 
	 * @param key 自定义key
	 * @return 封装key列表
	 */
	public String getKey(String key) {
		return StringUtils.join(getKeyScope(key), REDIS_DELIMITER);
	}
	
	public String getSiteKey(String key) {
		return StringUtils.join(getSiteKeyScope(key), REDIS_DELIMITER);
	}
	
	public String getAppKey(String key) {
		String keys = StringUtils.join(getAppKeyScope(key), REDIS_DELIMITER);
		//logger.info("====> getAppKey key:{} [key={} ]", keys, key);
		return keys;
	}

	/**
	 * 统一封装 index
	 *
	 * @param index 自定义index
	 * @return 封装index表
	 */
	public String getIndex(String index) {
		return StringUtils.join(getKeyScope(index), ES_DELIMITER);
	}
	/**
	 * 统一封装 index
	 *
	 * @param index 自定义index
	 * @return 封装index表
	 */
	public String getIndex(String appName, String index) {
		return StringUtils.join(getKeyScope(appName, index), ES_DELIMITER);
	}

	/**
	 * 统一封装 topic
	 * 
	 * @param topic 自定义topic
	 * @return 封装topic表
	 */
	public String getTopic(String topic) {
		return StringUtils.join(getKeyScope(topic), TOPIC_DELIMITER);
	}
	
	/**
	 * 统一封装 topic(不带站点)
	 * 
	 * @param topic 自定义topic
	 * @return 封装topic表
	 */
	public String getAppTopic(String topic) {
		return StringUtils.join(getAppKeyScope(topic), TOPIC_DELIMITER);
	}
	
	public String getFinanceTopic(String topic) {
		String topicVal = KK_CLOUD + topic;
		//logger.info("====> getFinanceTopic key:{} [topic={} ]", topicVal, topic);
		return topicVal;
	}
}
