package cn.failbetter.daybreak.common.exception;

/**
 * @author sun 2019/4/26
 */
public class TokenException extends ApiException{

	public TokenException(String message) {
		super(message);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
