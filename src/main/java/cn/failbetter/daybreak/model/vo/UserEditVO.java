package cn.failbetter.daybreak.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author sun 2019/4/25
 */
@Getter
@Setter
public class UserEditVO extends UserVO {
	@NotEmpty(message = "password can not be empty!")
	@Length(max = 255)
	private String password;
}
