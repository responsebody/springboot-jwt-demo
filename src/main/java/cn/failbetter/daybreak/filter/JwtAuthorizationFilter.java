package cn.failbetter.daybreak.filter;

import cn.failbetter.daybreak.security.JwtHelper;
import cn.failbetter.daybreak.security.JwtToken;
import cn.failbetter.daybreak.service.TokenService;
import com.google.common.base.Preconditions;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static cn.failbetter.daybreak.config.SecurityConfig.AUTHORIZATION_HEADER;
import static cn.failbetter.daybreak.config.SecurityConfig.TOKEN_BEARER_PREFIX;

/**
 * @author sun 2019/4/26
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final TokenService tokenService;
	private final JwtHelper jwtHelper;

	public JwtAuthorizationFilter(@Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService,
			TokenService tokenService, JwtHelper jwtHelper) {
		this.userDetailsService = userDetailsService;
		this.tokenService = tokenService;
		this.jwtHelper = jwtHelper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.debug("processing authentication for '{}'", request.getRequestURI());

		String header = request.getHeader(AUTHORIZATION_HEADER);
		if (!StringUtils.startsWith(header, TOKEN_BEARER_PREFIX)) {
			log.warn("couldn't find bearer string, will ignore the header");
			chain.doFilter(request, response);
			return;
		}


		JwtToken jwtToken = (JwtToken) tokenService.getToken(jwtHelper.extractToken(header));

		if (Objects.isNull(jwtToken)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no ");
			return;
		}
		try {
			Claims claims = jwtHelper.validateToken(jwtToken.getSecretKey(), jwtToken);
			String username = claims.getSubject();
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			log.info("authorized user '{}', setting security context", username);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}


		chain.doFilter(request, response);

	}

}
