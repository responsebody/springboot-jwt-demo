package cn.failbetter.daybreak.repository;

import cn.failbetter.daybreak.common.repository.ExtendedRepository;
import cn.failbetter.daybreak.model.dataobject.User;
import org.springframework.stereotype.Repository;


/**
 * @author sun 2019/4/25
 */
@Repository
public interface UserRepository extends ExtendedRepository<User, Long> {

	User findByUsername(String username);

	boolean existsUserByUsername(String username);

}
