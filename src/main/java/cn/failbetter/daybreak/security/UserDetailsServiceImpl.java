package cn.failbetter.daybreak.security;

import cn.failbetter.daybreak.model.dataobject.User;
import cn.failbetter.daybreak.model.dto.UserDTO;
import cn.failbetter.daybreak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author sun 2019/4/25
 */
@Service("jwtUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserDTO user = userService.find(s);
		return new JwtUser(user);
	}
}
