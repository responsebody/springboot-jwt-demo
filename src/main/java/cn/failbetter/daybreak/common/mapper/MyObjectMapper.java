package cn.failbetter.daybreak.common.mapper;

import cn.failbetter.daybreak.config.AppConfig;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.TimeZone;

/**
 * 全局json序列号配置
 * @author zhangll
 *
 */
public class MyObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;
	private static final ObjectMapper INSTANCE = new MyObjectMapper();

	public MyObjectMapper() {
		super();

        this.setTimeZone(TimeZone.getTimeZone(AppConfig.TIME_ZONE));

		//输出空对象
		this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 属性序列化范围
        this.setSerializationInclusion(JsonInclude.Include.ALWAYS);

		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        this.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);

        //this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

	}

	private class NullSerializer extends JsonSerializer<Object> {

		public void serialize(Object value, JsonGenerator jgen,
		                      SerializerProvider provider) throws IOException {
			jgen.writeString("");
		}
	}
	public static ObjectWriter getWriter() {
		return INSTANCE.writer();
	}

	public static ObjectReader getReader(Class<?> type) {
		return INSTANCE.readerFor(type);
	}

	public static ObjectReader getReader(TypeReference<?> type) {
		return INSTANCE.readerFor(type);
	}

	@Override
	public ObjectMapper copy() {
		return this;
	}
}
