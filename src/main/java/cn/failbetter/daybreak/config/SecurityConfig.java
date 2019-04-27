package cn.failbetter.daybreak.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author sun 2019/4/25
 */
@Getter
@Setter
@Component
public class SecurityConfig {


	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	public static final long EXPIRATION = 12L * 60L * 60L * 1000L;

	public static final String USER_KEY_PREFIX = "user:token:";


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
