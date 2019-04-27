package cn.failbetter.daybreak.service;

import cn.failbetter.daybreak.security.JwtUser;

/**
 * @author sun 2019/4/27
 */
public interface AuthenticationService {

	String createAuthenticationToken(JwtUser jwtUser);
}
