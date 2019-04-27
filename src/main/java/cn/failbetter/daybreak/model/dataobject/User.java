package cn.failbetter.daybreak.model.dataobject;

import cn.failbetter.daybreak.common.constant.UserRole;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

/**
 * @author sun 2019/4/25
 */
@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String password;
	private boolean statusCode;
	private String name;
	private String homepage;
	private String profilePicUrl;
	private String email;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role_rel", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "roleCode")
	private Set<String> relations;

	private static final Set<String> DEFAULT_ROLES = ImmutableSet.of(UserRole.AUTHOR.value());

	public void setDefaultRoles() {

		if (CollectionUtils.isEmpty(relations)) {
			this.relations = Sets.newHashSet(UserRole.AUTHOR.value());
		}
		this.statusCode = true;

	}

}
