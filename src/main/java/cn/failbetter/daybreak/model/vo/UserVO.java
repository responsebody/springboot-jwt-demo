package cn.failbetter.daybreak.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author sun 2019/4/25
 */
@Data
public class UserVO {
	@NotEmpty
	@Length(max = 255)
	private String username;
	@Length(max = 255)
	private String name;
	@Length(max = 255)
	private String homepage;
	@Length(max = 255)
	private String profilePicUrl;
	@Length(max = 255)
	private String email;
	private List<String> relations;
}
