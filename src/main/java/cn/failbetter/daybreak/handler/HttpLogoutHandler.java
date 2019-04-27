package cn.failbetter.daybreak.handler;

import cn.failbetter.daybreak.security.JwtHelper;
import cn.failbetter.daybreak.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sun 2019/4/26
 */
@Component
public class HttpLogoutHandler implements LogoutSuccessHandler {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private JwtHelper jwtHelper;

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException {
		String token = jwtHelper.extractToken(httpServletRequest);

		if (StringUtils.isNotEmpty(token)){
			tokenService.deleteToken(token);
		}
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		httpServletResponse.getWriter().flush();
	}
}
