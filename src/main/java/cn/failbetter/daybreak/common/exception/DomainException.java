package cn.failbetter.daybreak.common.exception;

/**
 * @author sun 2019/4/25
 */
public class DomainException extends RuntimeException {

	public DomainException() {
		super();
	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}
}
