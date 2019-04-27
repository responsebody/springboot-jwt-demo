package cn.failbetter.daybreak.model.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author sun 2019/4/27
 */
@Data
public class UserDTO {

	private Long id;

	private String username;
	private String password;
	private boolean statusCode;
	private String name;
	private String homepage;
	private String profilePicUrl;
	private String email;
	private Set<String> relations;
}
