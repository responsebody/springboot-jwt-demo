package cn.failbetter.daybreak.common.web.response;

import lombok.Getter;

/**
 * @author sun 2019/4/25
 */
@Getter

public class BeanResponse extends Response {
	private Object result;

	private BeanResponse(Object result) {
		this.result = result;
	}

	public static BeanResponse getBeanResponse(Object o) {
		return new BeanResponse(o);
	}
}
