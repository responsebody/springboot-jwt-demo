package cn.failbetter.daybreak.repository;

import cn.failbetter.daybreak.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author sun 2019/4/26
 */
@Repository
public class TokenRepository {

	@Autowired
	@Qualifier("tokenRedisTemplate")
	private RedisTemplate redisTemplate;

	public void setSecretKey(String key, JwtToken jwtToken) {
		redisTemplate.opsForValue().set(key, jwtToken);
	}

	public void setSecretKey(String key, String token) {
		redisTemplate.opsForValue().set(key, token);
	}


	public Boolean setKeyExpiration(String key, Date exp) {
		Boolean r = redisTemplate.expireAt(key, exp);
		System.out.println(redisTemplate.getExpire(key, TimeUnit.MINUTES));
		return r;
	}

	public void delete(String key) {
		redisTemplate.delete(key);
	}

	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}
