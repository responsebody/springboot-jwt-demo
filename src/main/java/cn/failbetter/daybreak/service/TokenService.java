package cn.failbetter.daybreak.service;

import cn.failbetter.daybreak.security.JwtToken;

import java.util.Date;

/**
 * @author sun 2019/4/26
 */
public interface TokenService {

	void setToken(String key, JwtToken jwtToken);

	void setToken(String key, String token);

	Boolean setKeyExpiration(String key, Date exp);

	void deleteToken(String key);

	Object getToken(String key);

}
