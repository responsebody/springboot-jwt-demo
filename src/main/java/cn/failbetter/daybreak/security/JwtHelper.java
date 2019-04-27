package cn.failbetter.daybreak.security;

import cn.failbetter.daybreak.config.AppConfig;
import cn.failbetter.daybreak.config.SecurityConfig;
import com.google.common.base.Preconditions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static cn.failbetter.daybreak.config.SecurityConfig.*;
import static cn.failbetter.daybreak.security.JwtConstant.*;

/**
 * @author sun 2019/4/25
 */
@Component
public class JwtHelper {

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private SecurityConfig securityConfig;

	public JwtToken generateAccessToken(String username, boolean isRememberMe) throws NoSuchAlgorithmException {
		Preconditions.checkState(StringUtils.isNotEmpty(username), "Username is null");

		DateTime currentTime = DateTime.now();
		Date expirationTime = currentTime.plus(new Duration(EXPIRATION)).toDate();

		SecretKey secretKey = generateSecretKey();
		String token = Jwts.builder().setIssuer(appConfig.getAppName()).setSubject(username)
				.setIssuedAt(currentTime.toDate()).setExpiration(expirationTime)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();

		return new JwtToken(token, appConfig.getAppName(), username, secretKey, currentTime.toDate(), expirationTime);
	}

	public String extractToken(HttpServletRequest request) {
		String headerToken = request.getHeader(AUTHORIZATION_HEADER);
		return extractToken(headerToken);
	}

	public String extractToken(String headerToken) {
		return headerToken.replace(TOKEN_BEARER_PREFIX, StringUtils.EMPTY);
	}

	private SecretKey generateSecretKey() throws NoSuchAlgorithmException {
		return KeyGenerator.getInstance("AES").generateKey();
	}

	public Claims validateToken(SecretKey key, JwtToken jwtToken) {
		return Jwts.parser()
				.requireIssuer(jwtToken.getIssuer())
				.requireSubject(jwtToken.getSubject())
				.requireIssuedAt(jwtToken.getIssueDate())
				.requireExpiration(jwtToken.getExpDate())
				.setSigningKey(key)
				.parseClaimsJws(jwtToken.getToken())
				.getBody();
	}




}
