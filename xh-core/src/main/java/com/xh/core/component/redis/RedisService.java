package com.xh.core.component.redis;

import com.xh.core.component.ApplicationKeyGenerate;
import com.xh.core.response.CommonStatusCode;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 * @description: redis服务类
 * @author: js-reke
 * @create: 2019-11-12 13:43
 */
@Component
public class RedisService {
	
	public static final int KEY_TYPE_SITE = 1;
	public static final int KEY_TYPE_APP = 2;

	@Resource
	private ApplicationKeyGenerate appKeyGenerate;

	@Resource
	private RedisTemplate<Object, Object> redisTemplate;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	public String getFormatKey(String key,int keyType) {
		if(keyType == KEY_TYPE_SITE) {
			return appKeyGenerate.getSiteKey(key);
		}else if(keyType == KEY_TYPE_APP) {
			return appKeyGenerate.getAppKey(key);
		}
		return appKeyGenerate.getKey(key);
	}
	
	public String getFormatKey(String key) {
		return appKeyGenerate.getKey(key);
	}
	
	public String getFormatSiteKey(String key) {
		return appKeyGenerate.getSiteKey(key);
	}
	
	public void setGlobleValue(String key, Object value) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(CommonStatusCode.REDIS_KEY_GLOBAL + key, value);
	}
	
	public void setGlobleSiteValue(String key, Object value) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(CommonStatusCode.REDIS_KEY_SITE_TYPE + key, value);
	}

	public void setValue(String key, Object value) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(getFormatKey(key,0), value);
	}
	
	public void setValue(String key, Object value,int keyType) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(getFormatKey(key,keyType), value);
	}
	
	public void setSiteValue(String key, Object value) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(getFormatKey(key,KEY_TYPE_SITE), value);
	}

	/**
	 * 存储object类型值（设置过期时间）
	 * @param key     自定义key
	 * @param value   存储值对象
	 * @param expirse 存活时间
	 */
	public void setValue(String key, Object value, long expirse) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(getFormatKey(key,0), value, Duration.ofMillis(expirse));
	}
	
	public void setValue(String key, Object value, long expirse,int keyType) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(getFormatKey(key,keyType), value, Duration.ofMillis(expirse));
	}
	
	
	public void setSiteValue(String key, Object value, long expirse) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(getFormatKey(key,KEY_TYPE_SITE), value, Duration.ofMillis(expirse));
	}
	
	public void setGlobleAppValue(String key, Object value,long expirse) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(CommonStatusCode.REDIS_KEY_SITE_TYPE + key, value, Duration.ofMillis(expirse));
	}
	
	
	public Object getGlobleAppValue(String key) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.get(getFormatKey(key,KEY_TYPE_APP));
	}
	
	public void setGlobleSiteValue(String key, Object value,long expirse) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(CommonStatusCode.REDIS_KEY_SITE_TYPE + key, value, Duration.ofMillis(expirse));
	}
	
	public void setGlobleValue(String key, Object value,long expirse) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		vo.set(CommonStatusCode.REDIS_KEY_GLOBAL + key, value, Duration.ofMillis(expirse));
	}
	
	public Object getGlobleSiteValue(String key) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.get(CommonStatusCode.REDIS_KEY_SITE_TYPE + key);
	}
	
	public Object getGlobleValue(String key) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.get(CommonStatusCode.REDIS_KEY_GLOBAL + key);
	}

	public Object getAndSet(String key, Object value) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.getAndSet(getFormatKey(key,0), value);
	}
	
	/**
	 * 如果键不存在则新增,存在则不改变已经有的值
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setIfAbsent(String key, Object value) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.setIfAbsent(getFormatKey(key,0), value);
	}

	/**
	 * 获取object类型值
	 * @param key 自定义key
	 * @return 存储值对象
	 */
	public Object getValue(String key) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.get(getFormatKey(key,0));
	}
	
	public Object getValue(String key,int keyType) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.get(getFormatKey(key,keyType));
	}
	
	
	public Object getSiteValue(String key) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.get(getFormatKey(key,KEY_TYPE_SITE));
	}
	
	@Deprecated
	public Object getObjectFullKey(String key) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.get(key);
	}

	/**
	 * 根据key删除值
	 * @param key 自定义key
	 */

	public Boolean delete(String key) {
		return redisTemplate.delete(getFormatKey(key,0));
	}
	
	public Boolean delete(String key,int keyType) {
		return redisTemplate.delete(getFormatKey(key,keyType));
	}
	
	public Boolean deleteGloble(String key) {
		return redisTemplate.delete(CommonStatusCode.REDIS_KEY_GLOBAL + key);
	}
	
	public Boolean deleteSite(String key) {
		return redisTemplate.delete(getFormatKey(key,KEY_TYPE_SITE));
	}
	
	public Boolean deleteGlobleSite(String key) {
		return redisTemplate.delete(CommonStatusCode.REDIS_KEY_SITE_TYPE + key);
	}

	/**
	 * 根据key操作double类型值
	 * @param key   自定义key
	 * @param value
	 */
	public Double incrementBy(String key, double value) {
		ValueOperations<Object, Object> vo = redisTemplate.opsForValue();
		return vo.increment(getFormatKey(key,0), value);
	}

	/**
	 * 存储set集合值
	 * @param key
	 * @param value
	 */
	public Long setAdd(String key, Object value) {
		SetOperations<Object, Object> so = redisTemplate.opsForSet();
		return so.add(getFormatKey(key,0), value);
	}

	/**
	 * 删除set集合值
	 * @param key
	 * @param value
	 */
	public Long setRemove(String key, Object value) {
		SetOperations<Object, Object> so = redisTemplate.opsForSet();
		return so.remove(getFormatKey(key,0), value);
	}

	/**
	 * 获得集合中元素数
	 * @param key
	 */
	public Long setSize(String key) {
		SetOperations<Object, Object> so = redisTemplate.opsForSet();
		return so.size(getFormatKey(key,0));
	}

	/**
	 * 判断value是否集合key的成员
	 * @param key
	 * @param value
	 */
	public Boolean setIsMember(String key, Object value) {
		SetOperations<Object, Object> so = redisTemplate.opsForSet();
		return so.isMember(getFormatKey(key,0), value);
	}

	/**
	 * 获取集合key中的所有成员
	 * @param key
	 */
	public Set<Object> setMembers(String key) {
		SetOperations<Object, Object> so = redisTemplate.opsForSet();
		return so.members(getFormatKey(key,0));
	}

	/**
	 * 存储set集合值
	 * @param key
	 * @param value
	 * @param scope
	 */
	public Boolean zSetAdd(String key, Object value, double scope) {
		ZSetOperations<Object, Object> zos = redisTemplate.opsForZSet();
		return zos.add(getFormatKey(key,0), value, scope);
	}

	/**
	 * 删除set集合值
	 * @param key
	 * @param objects
	 */
	public Long zSetRemove(String key, Object... objects) {
		ZSetOperations<Object, Object> zos = redisTemplate.opsForZSet();
		return zos.remove(getFormatKey(key,0), objects);
	}

	/**
	 * 获取set集合值
	 * @param key
	 * @param min
	 * @param max
	 */
	public Set<Object> zRangeByScore(String key, long min, long max) {
		ZSetOperations<Object, Object> zos = redisTemplate.opsForZSet();
		return zos.rangeByScore(getFormatKey(key,0), min, max);
	}

	/**
	 * 删除set集合值
	 * @param key
	 * @param min
	 * @param max
	 */
	public Long zSetRemoveRangeByScore(String key, long min, long max) {
		ZSetOperations<Object, Object> zos = redisTemplate.opsForZSet();
		return zos.removeRangeByScore(getFormatKey(key,0), min, max);
	}

	/**
	 * 向redis hash几何中存放一个元素
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public void hashSet(String key, String hashKey, Object value) {
		HashOperations<Object, Object, Object> ho = redisTemplate.opsForHash();
		ho.put(getFormatKey(key,0), hashKey, value);
	}

	/**
	 * 获取集合中的某个值
	 * @param key
	 * @param hashKey
	 */
	public Object hashGet(String key, String hashKey) {
		HashOperations<Object, Object, Object> ho = redisTemplate.opsForHash();
		return ho.get(getFormatKey(key,0), hashKey);
	}

	/**
	 * 删除map集合中一个或多个hash key对应的value
	 * @param key
	 * @param hashKeys
	 */
	public Long hashDelete(String key, String[] hashKeys) {
		HashOperations<Object, Object, Object> ho = redisTemplate.opsForHash();
		Object[] objects = { hashKeys };
		return ho.delete(getFormatKey(key,0), objects);
	}

	/**
	 * 获取过期时间
	 * @param key
	 */
	public long getExpire(String key) {
		Long expire = stringRedisTemplate.getExpire(getFormatKey(key,0));
		if (expire != null) {
			return expire;
		}
		return 0L;
	}

	/**
	 * 设置过期时间
	 * @param key
	 */
	public Boolean expire(String key, long expire) {
		return stringRedisTemplate.expire(getFormatKey(key,0), expire, TimeUnit.MILLISECONDS);
	}
	
	public Boolean expire(String key, long expire,int keyType) {
		return stringRedisTemplate.expire(getFormatKey(key,keyType), expire, TimeUnit.MILLISECONDS);
	}
	
	
	/**
	 * 设置过期时间
	 * @param key
	 */
	public Boolean expireSite(String key, long expire) {
		return stringRedisTemplate.expire(getFormatKey(key,0), expire, TimeUnit.MILLISECONDS);
	}

	/**
	 * key 不存在就设置
	 * @param key 键
	 * @param value 值
	 * @return 是否成功
	 */
	public Boolean setNotExists(String key, String value) {
		return stringRedisTemplate.opsForValue().setIfAbsent(getFormatKey(key,0), value);
	}

	/**
	 * key 不存在就设置
	 * @param key 键
	 * @param value 值
	 * @param timeOut 超时时间
	 * @return 是否成功
	 */
	public Boolean setNotExists(String key, String value, long timeOut) {
		return stringRedisTemplate.opsForValue().setIfAbsent(getFormatKey(key,0), value, timeOut, TimeUnit.MILLISECONDS);
	}

	/**
	 * 判断当前集合中是否已经存在hash key
	 * @param key
	 */
	public Boolean hasKey(String key) {
		return stringRedisTemplate.hasKey(getFormatKey(key,0));
	}

	/**
	 * 将数据添加到key对应的现有数据的左边
	 * @param key   list队列key
	 * @param value 插入的数据值
	 */
	public Long leftPush(String key, Object value) {
		return redisTemplate.opsForList().leftPush(getFormatKey(key,0), value);
	}

	/**
	 * 将数据添加到key对应的现有数据的右边
	 * @param key   list队列key
	 * @param value 插入的数据值
	 */
	public Long rightPush(String key, Object value) {
		return redisTemplate.opsForList().rightPush(getFormatKey(key,0), value);
	}

	/**
	 * 从key对应的现有数据的左边取出
	 * @param key   list队列key
	 */
	public Object leftPop(String key) {
		return redisTemplate.opsForList().leftPop(getFormatKey(key,0));
	}

	/**
	 * 从key对应的现有数据的左边取出
	 * @param key   list队列key
	 */
	public Object rightPop(String key) {
		return redisTemplate.opsForList().rightPop(getFormatKey(key,0));
	}
	
}
