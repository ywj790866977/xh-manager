package com.xh.core.config;

import com.xh.core.component.redis.RedisService;
import com.xh.core.utils.KKCloudUtils;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 拉单注单主库数据库连接池配置
 * @author: js-reke
 * @create: 2019-11-11 13:43
 */
@Configuration
public class KKCloudBaseConfig {
	
	@Value("${kkcloud.service.local-offset-hours:8}")
	private int localOffsetHours;

	@Resource
	private RedisService redisService;

	@PostConstruct
	public void init() {
		KKCloudUtils.setLocalOffsetHours(localOffsetHours);
	}

}
