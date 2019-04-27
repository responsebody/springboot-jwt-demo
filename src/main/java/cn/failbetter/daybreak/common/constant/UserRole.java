package cn.failbetter.daybreak.common.constant;

import lombok.Getter;

/**
 * @author sun 2019/4/25
 */
@Getter
public enum UserRole {
	//	role
	ADMIN("ROLE_ADMIN"), AUTHOR("ROLE_AUTHOR"), USER("ROLE_USER");

	private String name;

	UserRole(String name) {
		this.name = name;
	}

	public String value() {
		return this.name;
	}}
