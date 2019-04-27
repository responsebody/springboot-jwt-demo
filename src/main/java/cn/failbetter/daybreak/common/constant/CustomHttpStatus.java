package cn.failbetter.daybreak.common.constant;

/**
 * @author sun 2019/4/25
 */
public enum CustomHttpStatus {
	//	错误
	SERVICE_EXCEPTION(599, "Service Exception");

	private final int value;
	private final String reasonPhrase;

	private CustomHttpStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}}
