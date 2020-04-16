//package com.xh.core.config;
//
//import com.kkcloud.core.config.support.AutoDBHikariDataSource;
//import com.kkcloud.core.config.support.DynamicDataSource;
//import com.kkcloud.core.config.support.DynamicDataSourceTransactionManager;
//import com.kkcloud.core.user.UserContext;
//import com.xh.core.config.support.AutoDBHikariDataSource;
//import com.xh.core.config.support.DynamicDataSource;
//import com.xh.core.config.support.DynamicDataSourceTransactionManager;
//import com.xh.core.user.UserContext;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * @description: 拉单注单主库数据库连接池配置
// * @author: js-reke
// * @create: 2019-11-11 13:43
// */
//@Configuration
//@ConditionalOnProperty(name = "spring.datasource.url", matchIfMissing = false)
//@MapperScan(basePackages = { "com.kkcloud.**.mapper" })
//@EnableTransactionManagement(proxyTargetClass = true)
//public class DynamicDataSourceConfig {
//
//	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceConfig.class);
//
//	public static int siteVal;
//
//	public int getSiteVal() {
//		return siteVal;
//	}
//
//	@Value("${kkcloud.siteval:0}")
//	public void setSiteVal(int siteVal) {
//		DynamicDataSourceConfig.siteVal = siteVal;
//	}
//
//	@Resource
//	private UserContext userContext;
//
//	@Primary
//	@Bean(name = "masterDS", destroyMethod = "close")
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource MasterDataSource(DataSourceProperties dsProps) {
//		return DataSourceBuilder.create(dsProps.getClassLoader()).type(AutoDBHikariDataSource.class)
//				.driverClassName(dsProps.determineDriverClassName()).url(dsProps.determineUrl())
//				.username(dsProps.determineUsername()).password(dsProps.determinePassword()).build();
//	}
//
//	@Bean(name = "slaveDS", destroyMethod = "close")
//	@ConfigurationProperties(prefix = "spring.datasource.slave")
//	public DataSource SlaveDataSource(DataSourceProperties dsProps) {
//		return DataSourceBuilder.create(dsProps.getClassLoader()).type(AutoDBHikariDataSource.class)
//				.driverClassName(dsProps.determineDriverClassName()).url(dsProps.determineUrl())
//				.username(dsProps.determineUsername()).password(dsProps.determinePassword()).build();
//	}
//
//
//	@Bean
//	public DynamicDataSource dynamicDataSource(@Qualifier("masterDS") DataSource master_ds, @Qualifier("slaveDS") DataSource slave_ds) {
//		DynamicDataSource dynamicDataSource = new DynamicDataSource(userContext);
//		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
//
//		targetDataSources.put("master", master_ds);
//		targetDataSources.put("slave", slave_ds);
//		dynamicDataSource.setTargetDataSources(targetDataSources);
//
//		List<Object> slaveDataSources = new ArrayList<Object>();
//		slaveDataSources.add("slave");
//		//slaveDataSources.add("master");
//
//		dynamicDataSource.setDefaultTargetDataSource(master_ds);
//		dynamicDataSource.setSlaveDataSources(slaveDataSources);
//
//		return dynamicDataSource;
//
//	}
//
//
//	@Bean
//	public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource, UserContext userContext) throws SQLException, IOException {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource);
//		try {
//			sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*Mapper.xml"));
//		} catch (IOException ex) {
//			logger.debug(ex.getMessage());
//		}
//
//		Properties properties = new Properties();
//		properties.setProperty("sqlType", "mysql");
//		sqlSessionFactoryBean.setConfigurationProperties(properties);
//
//		return sqlSessionFactoryBean;
//	}
//
//	@Bean(name = "txManager")
//	public DynamicDataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) {
//		DynamicDataSourceTransactionManager dynamicDataSourceTransactionManager = new DynamicDataSourceTransactionManager();
//		dynamicDataSourceTransactionManager.setDataSource(dataSource);
//		return dynamicDataSourceTransactionManager;
//	}
//}
