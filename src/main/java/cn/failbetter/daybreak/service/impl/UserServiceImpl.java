package cn.failbetter.daybreak.service.impl;

import cn.failbetter.daybreak.model.convert.UserConvert;
import cn.failbetter.daybreak.model.dataobject.User;
import cn.failbetter.daybreak.model.dto.UserDTO;
import cn.failbetter.daybreak.repository.UserRepository;
import cn.failbetter.daybreak.service.UserService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

/**
 * @author sun 2019/4/25
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Cacheable(value = "user")
	public UserDTO find(@NotEmpty String username) {
		return UserConvert.INSTANCE.convert2DTO(userRepository.findByUsername(username));
	}

	@Override
	public User save(User user) {

		Preconditions.checkState(!userRepository.existsUserByUsername(user.getUsername()),
				String.format("User [%s] has exists!", user.getUsername()));

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
