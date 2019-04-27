package cn.failbetter.daybreak.service.impl;

import cn.failbetter.daybreak.repository.TokenRepository;
import cn.failbetter.daybreak.security.JwtToken;
import cn.failbetter.daybreak.service.TokenService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

import static cn.failbetter.daybreak.config.SecurityConfig.USER_KEY_PREFIX;

/**
 * @author sun 2019/4/26
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void setToken(String key, JwtToken jwtToken) {
		tokenRepository.setSecretKey(USER_KEY_PREFIX + key, jwtToken);
	}

	@Override
	public void setToken(String key, String token) {
		tokenRepository.setSecretKey(USER_KEY_PREFIX + key, token);
	}

	@Override
	public Boolean setKeyExpiration(String key, Date exp) {
		return tokenRepository.setKeyExpiration(USER_KEY_PREFIX + key, exp);
	}

	@Override
	public void deleteToken(String key) {
		tokenRepository.delete(USER_KEY_PREFIX + key);
	}

	@Override
	public Object getToken(String key) {
		return tokenRepository.get(USER_KEY_PREFIX + key);
	}
}
