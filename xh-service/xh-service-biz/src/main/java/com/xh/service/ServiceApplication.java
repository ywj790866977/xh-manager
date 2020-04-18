package com.xh.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ServiceApplication
 *
 * @author js-rubyle
 * @date 2020/4/18 11:39
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class,args);
	}
}
