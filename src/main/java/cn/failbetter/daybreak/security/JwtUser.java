package cn.failbetter.daybreak.security;

import cn.failbetter.daybreak.model.dataobject.User;
import cn.failbetter.daybreak.model.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author sun 2019/4/25
 */
@NoArgsConstructor
@Getter
@ToString(exclude = {"password"})
public class JwtUser implements UserDetails {

	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;


	public JwtUser(UserDTO user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = user.getRelations().stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
