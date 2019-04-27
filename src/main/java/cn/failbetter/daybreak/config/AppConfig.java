package cn.failbetter.daybreak.config;

import cn.failbetter.daybreak.common.mapper.MyObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

/**
 * @author sun 2019/4/26
 */
@Data
@Component
public class AppConfig {

	@Value("${spring.application.name}")
	private String appName;

	public static final String TIME_ZONE = "GMT+8";


	@Bean
	public HttpMessageConverters customConverters() {
		HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(new MyObjectMapper());
		return new HttpMessageConverters(converter);
	}

}
