package cn.failbetter.daybreak.service;

import cn.failbetter.daybreak.model.dataobject.User;
import cn.failbetter.daybreak.model.dto.UserDTO;

/**
 * @author sun 2019/4/25
 */
public interface UserService {

	UserDTO find(String username);

	User save(User user);
}
