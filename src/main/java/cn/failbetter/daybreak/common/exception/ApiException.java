package cn.failbetter.daybreak.common.exception;

/**
 * @author sun 2019/4/26
 */
public class ApiException extends RuntimeException{

	public ApiException(String message) {
		super(message);
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
