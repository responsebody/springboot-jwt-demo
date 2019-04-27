package cn.failbetter.daybreak.utils;

import cn.failbetter.daybreak.common.web.response.Response;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

/**
 * @author sun 2019/4/26
 */
public final class WebUtils {

	public static void writeJson(Response errorResponse, HttpServletResponse response) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(), JsonEncoding.UTF8);
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			jsonGenerator.writeObject(errorResponse);
			jsonGenerator.flush();
			jsonGenerator.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
