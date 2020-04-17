package com.xh.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * MonitorApplication
 *
 * @author js-rubyle
 * @date 2020/4/17 17:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class MonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class,args);
	}
}
