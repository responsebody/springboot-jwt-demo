package cn.failbetter.daybreak.common.exception;

import cn.failbetter.daybreak.common.constant.CustomHttpStatus;
import cn.failbetter.daybreak.common.web.response.MessageResponse;
import cn.failbetter.daybreak.common.web.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author sun 2019/4/25
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlingController {


	@ExceptionHandler(DomainException.class)
	public Response handle(DomainException e, HandlerMethod m, HttpServletResponse httpServletResponse) {
		logMsg(e, m);
		setDomainResponseStatus(httpServletResponse);
		return MessageResponse.getMessageResponse(CustomHttpStatus.SERVICE_EXCEPTION.value(), e.getMessage());
	}

	@ExceptionHandler(TokenException.class)
	public Response handle(TokenException e, HandlerMethod m, HttpServletResponse httpServletResponse) {
		logMsg(e, m);
		setResponseStatus(HttpStatus.UNAUTHORIZED.value(), httpServletResponse);
		return MessageResponse.getMessageResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Response handle(IllegalArgumentException e, HandlerMethod m, HttpServletResponse httpServletResponse) {
		logMsg(e, m);
		setDomainResponseStatus(httpServletResponse);
		return MessageResponse.getMessageResponse(CustomHttpStatus.SERVICE_EXCEPTION.value(), e.getMessage());
	}

	@ExceptionHandler(IllegalStateException.class)
	public Response handle(IllegalStateException e, HandlerMethod m, HttpServletResponse httpServletResponse) {
		logMsg(e, m);
		setDomainResponseStatus(httpServletResponse);
		return MessageResponse.getMessageResponse(CustomHttpStatus.SERVICE_EXCEPTION.value(), e.getMessage());
	}

	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public Response handle(Exception e, HandlerMethod m, HttpServletResponse httpServletResponse) {
		logMsg(e, m);
		e.printStackTrace();
		setDomainResponseStatus(httpServletResponse);
		return MessageResponse.getMessageResponse(CustomHttpStatus.SERVICE_EXCEPTION.value(), e.getMessage());
	}

	private void logMsg(Exception e, HandlerMethod m) {
		log.info("CustomInterceptAdvice handle exception {}, method: {}", e.getMessage(), m.getMethod().getName());
	}

	private void setDomainResponseStatus(HttpServletResponse httpServletResponse) {
		setResponseStatus(CustomHttpStatus.SERVICE_EXCEPTION.value(), httpServletResponse);
	}

	private void setResponseStatus(int status, HttpServletResponse httpServletResponse) {
		httpServletResponse.setStatus(status);
	}
}
