package com.xh.common.datasource;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;

import com.xh.common.datasource.config.JdbcDynamicDataSourceProvider;
import com.xh.common.datasource.config.LastParamDsProcessor;
import com.xh.common.datasource.config.XhDataSourceProperties;
import lombok.AllArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 动态数据源切换配置
 * @author rubyle
 * @date 2020-04-30
 */
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(XhDataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {
	private final StringEncryptor stringEncryptor;
	private final XhDataSourceProperties properties;

	@Bean
	public DynamicDataSourceProvider dynamicDataSourceProvider() {
		return new JdbcDynamicDataSourceProvider(stringEncryptor, properties);
	}

	@Bean
	public DsProcessor dsProcessor() {
		return new LastParamDsProcessor();
	}
}