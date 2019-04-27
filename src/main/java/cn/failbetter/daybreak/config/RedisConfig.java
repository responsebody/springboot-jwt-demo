package cn.failbetter.daybreak.config;

import cn.failbetter.daybreak.common.mapper.MyObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sun 2019/4/26
 */
@Configuration
public class RedisConfig {

	@Bean(name = "tokenRedisTemplate")
	public RedisTemplate<String, Object> tokenRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		MyObjectMapper myObjectMapper = new MyObjectMapper();
		myObjectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		myObjectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(myObjectMapper);
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}



}
