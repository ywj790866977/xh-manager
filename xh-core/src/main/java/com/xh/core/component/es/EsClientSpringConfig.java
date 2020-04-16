package com.xh.core.component.es;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Reke
 * @date 2019/11/13 16:21
 * @Description EsClient 请求类 创建工厂
 */
@Configuration
@ConditionalOnProperty(name = "elasticSearch.hostlist")
public class EsClientSpringConfig {

	private static int CONNECT_TIMEOUT_MILLIS = 100000;
	private static int SOCKET_TIMEOUT_MILLIS = 300000;
	private static int CONNECTION_REQUEST_TIMEOUT_MILLIS = 500000;
	private static int MAX_CONN_PER_ROUTE = 10;
	private static int MAX_CONN_TOTAL = 30;

	/**
	 * 连接数量
	 */
	@Value("${elasticSearch.client.connectNum:8}")
	private Integer connectNum;

	/**
	 * 路由次数
	 */
	@Value("${elasticSearch.client.connectPerRoute:3}")
	private Integer connectPerRoute;

	/**
	 * es接点 host列表 eg：47.56.104.6:9200
	 */
	@Value("${elasticSearch.hostlist}")
	private String hostlist;

	private RestClientBuilder builder;
	private RestClient restClient;
	private RestHighLevelClient restHighLevelClient;

	private static final Logger logger = LoggerFactory.getLogger(EsClientSpringConfig.class);


	@PostConstruct
	public void init() {
		builder = RestClient.builder(hostsToHttpHost(hostlist));
		setConnectTimeOutConfig();
		setMutiConnectConfig();
		restClient = builder.build();
		restHighLevelClient = new RestHighLevelClient(builder);
		logger.debug("init ES factory");
	}

	@Bean
	@Scope("singleton")
	public RestClient getRestClient() {
		return restClient;
	}

	@Bean(name = "restHighLevelClient")
	@Scope("singleton")
	public RestHighLevelClient getRestHighLevelClient() {
		return restHighLevelClient;
	}

	@PreDestroy
	public void close() {
		if (restClient != null) {
			try {
				restClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


    private HttpHost[] hostsToHttpHost(String hosts) {
        //解析hostlist配置信息
        String[] hostArray = hosts.split("[,;]+");
        //创建HttpHost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHostArray = new HttpHost[hostArray.length];
        for (int i = 0; i < hostArray.length; i++) {
            String[] item = hostArray[i].split(":");
            if(item.length < 2) {
            	logger.error("error host: {}", hostArray[i]);
            	continue;
            }
            httpHostArray[i] = new HttpHost(item[0], Integer.parseInt(item[1]), "http");
        }
        return httpHostArray;
    }

	/**
	 * 配置连接时间延时
	 */
	private void setConnectTimeOutConfig() {
		builder.setRequestConfigCallback(requestConfigBuilder -> {
			requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
			requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
			requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
			return requestConfigBuilder;
		});
	}

	/**
	 * 使用异步httpclient时设置并发连接数
	 */
	private void setMutiConnectConfig() {
		builder.setHttpClientConfigCallback(httpClientBuilder -> {
			httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
			httpClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
			return httpClientBuilder;
		});
	}

}
