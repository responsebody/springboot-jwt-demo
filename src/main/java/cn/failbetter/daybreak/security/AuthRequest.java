package cn.failbetter.daybreak.security;

import lombok.Data;

/**
 * @author sun 2019/4/26
 */
@Data
public class AuthRequest {
	private String username;
	private String password;
	private Integer isRememberMe;
}
