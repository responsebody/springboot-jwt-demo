package cn.failbetter.daybreak.service.impl;

import cn.failbetter.daybreak.common.exception.TokenException;
import cn.failbetter.daybreak.config.SecurityConfig;
import cn.failbetter.daybreak.security.JwtHelper;
import cn.failbetter.daybreak.security.JwtToken;
import cn.failbetter.daybreak.security.JwtUser;
import cn.failbetter.daybreak.service.AuthenticationService;
import cn.failbetter.daybreak.service.TokenService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;

import static cn.failbetter.daybreak.config.SecurityConfig.EXPIRATION;

/**
 * @author sun 2019/4/27
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtHelper helper;
	@Autowired
	private TokenService tokenService;

	@Override
	public String createAuthenticationToken(JwtUser jwtUser) {

		authenticate(jwtUser.getUsername(), jwtUser.getPassword());

		String token = (String) tokenService.getToken(jwtUser.getUsername());

		if (Objects.nonNull(token)) {
			resetExpTime(jwtUser.getUsername(), token);
			return token;
		} else {
			UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUser.getUsername());
			try {
				JwtToken jwtToken = helper.generateAccessToken(userDetails.getUsername(), false);
				saveToken(jwtToken);
				return token;
			} catch (NoSuchAlgorithmException e) {
				throw new TokenException("Generate token error!", e);
			}
		}
	}

	private void saveToken(JwtToken jwtToken) {
		Date expirationTime = getExpirationTime();

		tokenService.setToken(jwtToken.getToken(), jwtToken);
		tokenService.setKeyExpiration(jwtToken.getToken(), expirationTime);

		tokenService.setToken(jwtToken.getSubject(), jwtToken.getToken());
		tokenService.setKeyExpiration(jwtToken.getToken(), expirationTime);

	}

	private Date getExpirationTime() {
		return DateTime.now().plus(EXPIRATION).toDate();
	}

	private void resetExpTime(String username, String token) {
		Date expirationTime = getExpirationTime();
		tokenService.setKeyExpiration(username, expirationTime);
		tokenService.setKeyExpiration(token, expirationTime);
	}

	/**
	 * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
	 */
	private void authenticate(String username, String password) throws AuthenticationException {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new TokenException("User is disabled!", e);
		} catch (BadCredentialsException e) {
			throw new TokenException("Bad credentials!", e);
		}
	}
}
