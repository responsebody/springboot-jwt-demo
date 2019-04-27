package cn.failbetter.daybreak.common.web;

import cn.failbetter.daybreak.common.web.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author sun 2019/4/25
 */
@Slf4j
@ControllerAdvice
public class CustomResponseAdvice implements ResponseBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
			ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

		if (o instanceof Response) {
			return o;
		}
		log.info("拦截测试");
		return Response.success(o);
	}
}
