package cn.failbetter.daybreak.common.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author sun 2019/4/25
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse extends Response {

	private int status;
	private String message;

	public static MessageResponse getMessageResponse() {
		return new MessageResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
	}

	public static MessageResponse getMessageResponse(String message) {
		return new MessageResponse(HttpStatus.OK.value(), message);
	}

	public static MessageResponse getMessageResponse(int status, String message) {
		return new MessageResponse(status, message);
	}
}
