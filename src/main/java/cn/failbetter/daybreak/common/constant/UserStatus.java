package cn.failbetter.daybreak.common.constant;

import lombok.Getter;

/**
 * @author sun 2019/4/25
 */
@Getter
public enum UserStatus {

	//	1 ENABLED，0 DISABLED
	ENABLED(1), DISABLED(0);
	private int code;

	private UserStatus(int code) {
		this.code = code;
	}}
