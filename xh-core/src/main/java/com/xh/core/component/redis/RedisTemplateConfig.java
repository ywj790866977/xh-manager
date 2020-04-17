//package com.xh.core.component.redis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * @author reke
// * @date 2019/11/11 Redis 配置类
// */
//@Configuration
//public class RedisTemplateConfig {
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//		redisTemplate.setKeySerializer(stringRedisSerializer);
//		redisTemplate.setHashKeySerializer(stringRedisSerializer);
//		redisTemplate.afterPropertiesSet();
//
//		return redisTemplate;
//	}
//
//	@Bean
//	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		StringRedisTemplate redisTemplate = new StringRedisTemplate();
//		redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//		redisTemplate.setKeySerializer(stringRedisSerializer);
//		redisTemplate.setHashKeySerializer(stringRedisSerializer);
//		redisTemplate.afterPropertiesSet();
//
//		return redisTemplate;
//	}
//}
