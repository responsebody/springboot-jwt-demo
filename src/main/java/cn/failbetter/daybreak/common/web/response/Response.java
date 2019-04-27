package cn.failbetter.daybreak.common.web.response;


import cn.failbetter.daybreak.utils.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author sun 2019/4/25
 */
public abstract class Response {

	public static Response success(Object o) {

		if (Objects.isNull(o)) {
			return new EmptyResponse();
		} else {
			return BeanResponse.getBeanResponse(o);
		}
	}

	public static void writeMessage(Response response, HttpServletResponse httpServletResponse) {
		WebUtils.writeJson(response, httpServletResponse);
	}

}
