//package com.xh.core.component;
//
//import com.alibaba.fastjson.JSON;
//import com.xh.core.component.redis.RedisService;
//import com.xh.core.exception.ServiceException;
//import com.xh.core.response.CommonStatusCode;
//import javax.annotation.Resource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.DigestUtils;
//
///**
// * @description: 幂等验证统一接口
// * @author: js-reke
// * @create: 2019-11-14 17:43
// */
//@Component
//public class IdempotentService {
//
//	@Value("${server.kkcloud.idempotent.expire:30000}")
//	private Long idempotent_expire;
//
//	@Resource
//	private RedisService redisService;
//
//	public static final String IDEMPOTENT_SCOPE = "idm:";
//
//	public boolean isProcessed(Object data) {
//		byte[] data_bts = JSON.toJSONString(data).getBytes();
//		String md5_str = DigestUtils.md5DigestAsHex(data_bts);
//		String md5_key = IDEMPOTENT_SCOPE + md5_str;
//
//		if (!redisService.setIfAbsent(md5_key, 1)) {
//			return true;
//		}
//		redisService.expire(md5_key, idempotent_expire);
//
//		return false;
//	}
//
//	public void processAssert(Object data) {
//		if (isProcessed(data)) {
//			throw new ServiceException(CommonStatusCode.IDEMPOTENT);
//		}
//	}
//
//	/**
//	 * 删除幂等KEY
//	 * @param data
//	 * @return
//	 */
//	public Boolean rollbackIdemptent(Object data) {
//		byte[] data_bts = JSON.toJSONString(data).getBytes();
//		String md5_str = DigestUtils.md5DigestAsHex(data_bts);
//		String md5_key = IDEMPOTENT_SCOPE + md5_str;
//		return redisService.delete(md5_key);
//	}
//
//
//}
